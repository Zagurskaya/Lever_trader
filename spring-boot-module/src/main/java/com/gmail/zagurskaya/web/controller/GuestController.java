package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.MailService;
import com.gmail.zagurskaya.service.UserRedisService;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.model.UserDTO;
import com.gmail.zagurskaya.service.model.UserRedisDTO;
import com.gmail.zagurskaya.web.request.ConfirmForm;
import com.gmail.zagurskaya.web.request.SignUpForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
public class GuestController {

    private static final Logger logger = LoggerFactory.getLogger(GuestController.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRedisService userRedisService;
    private final MailService mailService;

    @Autowired
    public GuestController(UserService userService, PasswordEncoder passwordEncoder, UserRedisService userRedisService, MailService mailService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRedisService = userRedisService;
        this.mailService = mailService;
    }

    @PostMapping("/signup")
    public ResponseEntity sendTokenToEmail(@RequestBody @Valid SignUpForm signUpRequest) {
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
        //todo validation userDate

        UserRedisDTO user = new UserRedisDTO();
        user.setId(token);
        user.setUsername(signUpRequest.getUsername());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(signUpRequest.getRole());
        userRedisService.add(user);

//        mailService.sendLinkToMail(signUpRequest.getEmail(),token);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(
            value = "/confirm",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<String> getCurrency(@RequestParam String token) {
        logger.info(" send Token to mail => " + token);
        UserRedisDTO userRedisDTO = userRedisService.getUserById(token);
        return new ResponseEntity<>("created user : " + userRedisDTO.getUsername(), HttpStatus.OK);
    }


    @PostMapping(value = "/confirm",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity saveCurrency(@RequestParam String token, @RequestBody @Valid ConfirmForm confirmForm) {
        if (!confirmForm.getPassword().equals(confirmForm.getRepeaPpassword())) {
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

        //todo validation name+role
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userRedisDTO.getUsername());
        userDTO.setFirstName(userRedisDTO.getFirstName());
        userDTO.setLastName(userRedisDTO.getLastName());
        userDTO.setEmail(userRedisDTO.getEmail());
        userDTO.setPassword(confirmForm.getPassword());
        userDTO.setCreatedData(LocalDate.now());
        userDTO.setRole(userRedisDTO.getRole().toUpperCase());
        userService.add(userDTO);
        logger.info(" save user with  => " + userDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
