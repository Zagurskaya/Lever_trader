package com.gmail.zagurskaya.web.controller;

import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.model.UserDTO;
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

@RestController
@RequestMapping("/api/auth")
public class GuestController {

    private static final Logger logger = LoggerFactory.getLogger(GuestController.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public GuestController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity sendTokenToEmail(@RequestBody @Valid SignUpForm signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }
        String token = passwordEncoder.encode(signUpRequest.getUsername());
        logger.info("Token => " + token);

//        todo token to email
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(
            value = "/confirm",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<SignUpForm> getCurrency(@RequestParam String token) {
        logger.info(" send Token to mail => " + token);
        //todo find by token in redis
        SignUpForm signUpRequest = null;
//        return new ResponseEntity<>(signUpRequest, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/confirm",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity saveCurrency(@RequestParam String token, @RequestBody @Valid ConfirmForm confirmForm) {
        if (!confirmForm.getPassword().equals(confirmForm.getRepeaPpassword())) {
            return new ResponseEntity<String>("Fail -> Password does not match!",
                    HttpStatus.BAD_REQUEST);
        }
        //todo find by token in redis
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("ivan");
        userDTO.setFirstName("Ivan");
        userDTO.setLastName("Ivan");
        userDTO.setEmail("ivan@tut.by");
        userDTO.setPassword("123456789");
        userDTO.setRole("ADMINISTRATOR");
        userService.add(userDTO);
        logger.info(" save user with  => " + userDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
