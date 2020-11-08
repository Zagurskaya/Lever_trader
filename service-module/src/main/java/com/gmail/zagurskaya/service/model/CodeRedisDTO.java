package com.gmail.zagurskaya.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class CodeRedisDTO implements Serializable {

    private String id;
    @NotBlank
    @Size(min = 3, max = 10)
    private String mail;

}
