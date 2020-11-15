package com.gmail.zagurskaya.web.validator;

import org.springframework.validation.Errors;

public class DateValidator {
    private static final String EMAIL_VALIDATION_REGEX =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";

    public static void fieldValidate(String fieldValue, String fieldName, String pattern, Errors errors) {

        if (fieldValue != null && !fieldValue.matches(pattern)) {
            errors.rejectValue(fieldName, "Invalid " + fieldName + " form");
        }
    }

    public static boolean isEmailValid(String fieldValue) {
        return fieldValue != null && fieldValue.toLowerCase().matches(EMAIL_VALIDATION_REGEX);
    }
}
