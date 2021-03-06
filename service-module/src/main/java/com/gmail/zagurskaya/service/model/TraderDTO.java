package com.gmail.zagurskaya.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class TraderDTO {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    private Boolean approved = false;

    private BigDecimal rating;
}
