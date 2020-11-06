package com.gmail.zagurskaya.web.validator;

import com.gmail.zagurskaya.service.model.UserDTO;
import com.gmail.zagurskaya.service.model.UserRedisDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserRedisValidator implements Validator {

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
        UserRedisDTO userRedisDTO = (UserRedisDTO) o;

        if (userRedisDTO.getEmail() != null && !userRedisDTO.getEmail().toLowerCase().matches(EMAIL_VALIDATION_REGEX)) {
            errors.rejectValue("email", "Invalid email form");
        }
        if (userRedisDTO.getUsername() != null && !userRedisDTO.getUsername().matches(INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX)) {
            errors.rejectValue("username", "Invalid username form");
        }
        if (userRedisDTO.getFirstName() != null && !userRedisDTO.getFirstName().matches(INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX)) {
            errors.rejectValue("firstName", "Invalid FirstName form");
        }
        if (userRedisDTO.getLastName() != null && !userRedisDTO.getLastName().matches(INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX)) {
            errors.rejectValue("lastName", "Invalid lastName form");
        }
        if (userRedisDTO.getRole() != null && !userRedisDTO.getRole().matches(INITIALS_SYMBOLS_VALIDATION_REGEX)) {
            errors.rejectValue("role", "Invalid role form");
        }

    }
}
