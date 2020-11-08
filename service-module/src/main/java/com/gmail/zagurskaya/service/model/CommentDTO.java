package com.gmail.zagurskaya.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class CommentDTO {

    private Long id;
    @NotNull
    @Size(max = 200)
    private String message;

    private LocalDate createdDate;

    private UserDTO user;

    private TraderDTO trader;

    private Long userId;

    private Long traderId;
    @NotNull
    private Long mark;

    private Boolean approved = false;
}
