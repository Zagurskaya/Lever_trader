package com.gmail.zagurskaya.web.validator;

import com.gmail.zagurskaya.service.model.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private static final String EMAIL_VALIDATION_REGEX =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX = "^[A-Za-z0-9]+$";
    private static final String INITIALS_SYMBOLS_VALIDATION_REGEX = "^[A-Za-z]+$";

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;
        DateValidator.fieldValidate(userDTO.getEmail().toLowerCase(), "email", EMAIL_VALIDATION_REGEX, errors);
        DateValidator.fieldValidate(userDTO.getUsername(), "username", INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX, errors);
        DateValidator.fieldValidate(userDTO.getFirstName(), "firstName", INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX, errors);
        DateValidator.fieldValidate(userDTO.getLastName(), "lastName", INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX, errors);
        DateValidator.fieldValidate(userDTO.getRole(), "role", INITIALS_SYMBOLS_VALIDATION_REGEX, errors);
        DateValidator.fieldValidate(userDTO.getPassword(), "password", INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX, errors);
    }
}
