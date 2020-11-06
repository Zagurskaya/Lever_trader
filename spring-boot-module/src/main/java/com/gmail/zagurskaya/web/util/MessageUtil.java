package com.gmail.zagurskaya.web.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class MessageUtil {
    public static String getValidationErrorMessage(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(bindingResult.getErrorCount()).append(" error(s): ");
        for (ObjectError error : bindingResult.getAllErrors()) {
            sb.append("[").append(error).append("] ");
        }
        return sb.toString();
    }
}
