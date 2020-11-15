package com.gmail.zagurskaya.web.validator;

import com.gmail.zagurskaya.service.model.CommentDTO;
import com.gmail.zagurskaya.web.request.NewTraderWithCommentForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CommentValidator implements Validator {

    public static final String ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN = "[a-zA-Z0-9\\s_-]{1,}";
    private static final String MARK_VALIDATION_REGEX = "[0-9]{1}";

    @Override
    public boolean supports(Class<?> clazz) {
        return CommentDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CommentDTO commentDTO = (CommentDTO) o;

        DateValidator.fieldValidate(commentDTO.getMessage(), "message", ALPHABET_NUMBER_UNDERSCORE_MINUS_BLANK_VALIDATE_PATTERN, errors);
        DateValidator.fieldValidate(commentDTO.getMark().toString(), "mark", MARK_VALIDATION_REGEX, errors);
    }
}
