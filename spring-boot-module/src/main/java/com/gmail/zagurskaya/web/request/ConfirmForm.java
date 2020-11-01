package com.gmail.zagurskaya.web.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ConfirmForm {
    @NotBlank
    @Size(min = 6, max = 50)
    private String password;

    @NotBlank
    @Size(min = 6, max = 50)
    private String repeaPpassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeaPpassword() {
        return repeaPpassword;
    }

    public void setRepeaPpassword(String repeaPpassword) {
        this.repeaPpassword = repeaPpassword;
    }

    @Override
    public String toString() {
        return "ConfirmForm{" +
                "password='" + password + '\'' +
                ", repeaPpassword='" + repeaPpassword + '\'' +
                '}';
    }
}