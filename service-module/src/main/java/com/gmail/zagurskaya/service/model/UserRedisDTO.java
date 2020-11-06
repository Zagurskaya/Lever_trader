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
public class UserRedisDTO implements Serializable {

    private String id;
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;
    @NotBlank
    @Size(min = 3, max = 50)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 50)
    private String lastName;
    @NotBlank
    @Size(min = 3, max = 50)
    private String email;
    @NotBlank
    @Size(min = 3, max = 50)
    private String role;

}
