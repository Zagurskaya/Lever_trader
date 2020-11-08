package com.gmail.zagurskaya.web.validator;

import com.gmail.zagurskaya.web.request.ResetForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ResetFormValidator implements Validator {
    private static final String NUMBER_VALIDATION_REGEX = "[0-9]{1,}";
    private static final String INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX = "[A-Za-z0-9]{1,}";

    @Override
    public boolean supports(Class<?> clazz) {
        return ResetForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ResetForm reset = (ResetForm) o;

        DateValidator.fieldValidate(reset.getCode(), "code", NUMBER_VALIDATION_REGEX, errors);
        DateValidator.fieldValidate(reset.getNewPassword(), "newPassword", INITIALS_SYMBOLS_AND_NUMBER_VALIDATION_REGEX, errors);
    }
}
