package com.gmail.zagurskaya.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
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
    private Date createdDate;
    @NotNull
    private UserDTO user;
    @NotNull
    private TraderDTO trader;
    @NotNull
    private Long roleId;
    @NotNull
    private Long traderId;
    @NotNull
    private Boolean approved;
}
