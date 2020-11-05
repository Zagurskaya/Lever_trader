package com.gmail.zagurskaya.web.validation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateValidation {

    public static boolean isDateValidForCreateUser(LocalDateTime createdData) {
        LocalDateTime now = LocalDateTime.now();
        long diff = ChronoUnit.SECONDS.between(now, createdData);
        return diff < 86400;
    }
}
