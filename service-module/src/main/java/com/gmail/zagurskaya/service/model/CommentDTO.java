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
    @NotNull
    private Long id;
    @NotNull
    @Size(max = 200)
    private String message;
    @NotNull
    private LocalDate createdDate;
    @NotNull
    private UserDTO user;
    @NotNull
    private TraderDTO trader;
    @NotNull
    private Long userId;
    @NotNull
    private Long traderId;
    @NotNull
    @Size(min = 1, max = 10)
    private Long mark;
    @NotNull
    private Boolean approved;
}
