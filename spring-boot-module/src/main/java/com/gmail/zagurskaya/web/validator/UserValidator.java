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

        if (userDTO.getEmail() != null && !userDTO.getEmail().toLowerCase().matches(EMAIL_VALIDATION_REGEX)) {
            errors.rejectValue("email", "Invalid email form");
        }
        if (userDTO.getUsername() != null && !userDTO.getUsername().matches(INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX)) {
            errors.rejectValue("username", "Invalid username form");
        }
        if (userDTO.getFirstName() != null && !userDTO.getFirstName().matches(INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX)) {
            errors.rejectValue("firstName", "Invalid FirstName form");
        }
        if (userDTO.getLastName() != null && !userDTO.getLastName().matches(INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX)) {
            errors.rejectValue("lastName", "Invalid lastName form");
        }
        if (userDTO.getRole() != null && !userDTO.getRole().matches(INITIALS_SYMBOLS_VALIDATION_REGEX)) {
            errors.rejectValue("role", "Invalid role form");
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().matches(INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX)) {
            errors.rejectValue("password", "Invalid password form");
        }
    }
}
