package com.gmail.zagurskaya.service.model;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentDTO {
    @NotNull
    private Long id;
    @NotNull
    @Size(max = 200)
    private String message;
    @NotNull
    private Date createdDate;
    @NotNull
    private UserDTO user ;
    @NotNull
    private TraderDTO trader ;
    @NotNull
    private Long roleId;
    @NotNull
    private Long traderId;
    @NotNull
    private Boolean approved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public TraderDTO getTrader() {
        return trader;
    }

    public void setTrader(TraderDTO trader) {
        this.trader = trader;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getTraderId() {
        return traderId;
    }

    public void setTraderId(Long traderId) {
        this.traderId = traderId;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", trader=" + trader +
                ", roleId=" + roleId +
                ", traderId=" + traderId +
                ", approved=" + approved +
                '}';
    }
}
