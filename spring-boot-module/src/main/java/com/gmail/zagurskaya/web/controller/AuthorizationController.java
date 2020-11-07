package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.CodeRedisService;
import com.gmail.zagurskaya.service.MailService;
import com.gmail.zagurskaya.service.UserRedisService;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.model.CodeRedisDTO;
import com.gmail.zagurskaya.service.model.UserDTO;
import com.gmail.zagurskaya.service.model.UserRedisDTO;
import com.gmail.zagurskaya.service.util.UserUtil;
import com.gmail.zagurskaya.web.request.ConfirmForm;
import com.gmail.zagurskaya.web.request.SignUpForm;
import com.gmail.zagurskaya.web.util.DateUtil;
import com.gmail.zagurskaya.web.util.MessageUtil;
import com.gmail.zagurskaya.web.validator.DateValidator;
import com.gmail.zagurskaya.web.validator.UserRedisValidator;
import com.gmail.zagurskaya.web.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Map;

import static com.gmail.zagurskaya.web.constant.URLConstant.URL_AUTH;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_AUTH_CONFIRM;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_AUTH_FORGOT_PASSWORD;
import static com.gmail.zagurskaya.web.constant.URLConstant.URL_AUTH_SIGN_UP;

@RestController
@RequestMapping(URL_AUTH)
public class AuthorizationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    private final UserService userService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final UserRedisService userRedisService;
    private final CodeRedisService codeRedisService;
    private final UserValidator userValidator;
    private final UserRedisValidator userRedisValidator;
    private final UserUtil userUtil;


    @Autowired
    public AuthorizationController(UserService userService, PasswordEncoder passwordEncoder, UserRedisService userRedisService, MailService mailService, CodeRedisService codeRedisService, UserValidator userValidator, UserRedisValidator userRedisValidator, UserUtil userUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRedisService = userRedisService;
        this.mailService = mailService;
        this.codeRedisService = codeRedisService;
        this.userValidator = userValidator;
        this.userRedisValidator = userRedisValidator;
        this.userUtil = userUtil;
    }

    @PostMapping(URL_AUTH_SIGN_UP)
    public ResponseEntity saveUserToRedisAndSendTokenToEmail(@RequestBody @Valid SignUpForm signUpRequest, BindingResult result) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }
        String token = passwordEncoder.encode(signUpRequest.getUsername());
        logger.info("Token => " + token);

        UserRedisDTO user = new UserRedisDTO();
        user.setId(token);
        user.setUsername(signUpRequest.getUsername());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(signUpRequest.getRole());

        userRedisValidator.validate(user, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(MessageUtil.getValidationErrorMessage(result), HttpStatus.BAD_REQUEST);
        }
        userRedisService.add(user);
//        mailService.sendLinkToMail(signUpRequest.getEmail(),token);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(
            value = URL_AUTH_CONFIRM,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<String> getUserFromRedisByToken(@RequestParam String token) {
        logger.info(" send Token to mail => " + token);
        UserRedisDTO userRedisDTO = userRedisService.getUserById(token);
        return new ResponseEntity<>(" user : " + userRedisDTO.getUsername() + " confirmed his link", HttpStatus.OK);
    }


    @PostMapping(value = URL_AUTH_CONFIRM,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity saveUser(@RequestParam String token, @RequestBody @Valid ConfirmForm confirmForm, BindingResult result) {
        if (!confirmForm.getPassword().equals(confirmForm.getRepeatPassword())) {
            return new ResponseEntity<>("Fail -> Password does not match!",
                    HttpStatus.BAD_REQUEST);
        }
        UserRedisDTO userRedisDTO = userRedisService.getUserById(token);

        if (userService.existsByUsername(userRedisDTO.getUsername())) {
            return new ResponseEntity<>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(userRedisDTO.getEmail())) {
            return new ResponseEntity<>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userRedisDTO.getUsername());
        userDTO.setFirstName(userRedisDTO.getFirstName());
        userDTO.setLastName(userRedisDTO.getLastName());
        userDTO.setEmail(userRedisDTO.getEmail());
        userDTO.setPassword(confirmForm.getPassword());
        userDTO.setCreatedData(LocalDate.now());

        userDTO.setRole(userRedisDTO.getRole().toUpperCase());
        userValidator.validate(userDTO, result);
        if (result.hasErrors()) {
            return new ResponseEntity<>(MessageUtil.getValidationErrorMessage(result), HttpStatus.BAD_REQUEST);
        }
        userService.add(userDTO);
        logger.info(" save user with  => " + userDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(URL_AUTH_FORGOT_PASSWORD)
    public ResponseEntity sendCodeForForgotPasswordToEmail(@RequestBody Map<String,String> request) {
        String email = request.get("email");
        if (!DateValidator.isEmailValid(email)) {
            return new ResponseEntity<>("Invalid email form", HttpStatus.BAD_REQUEST);
        }
        String activationСode = DateUtil.randomCode(5);
        logger.info(" code  => " + activationСode);

        CodeRedisDTO codeRedisDTO = new CodeRedisDTO();
        codeRedisDTO.setId(email);
        codeRedisDTO.setValue(activationСode);

        codeRedisService.add(codeRedisDTO);
//        mailService.sendCodeToMail(email, activationСode);

        return new ResponseEntity(HttpStatus.OK);
    }
}
