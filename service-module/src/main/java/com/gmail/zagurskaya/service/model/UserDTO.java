package com.gmail.zagurskaya.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class UserDTO {

//    @NotNull
    private Long id;
    @NotNull
    @Size(max = 50)
    private String username;
//    @NotNull
//    @Size(max = 100)
    private String password;
    @NotNull
    private String lastName;
    @NotNull
    private String firstName;

    @NotNull
    private String email;

    @NotNull
    private Date createdData;

    private String role;
    @NotNull
    private Long roleId;

//    @NotNull
    private Boolean isNotActive;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedData() {
        return createdData;
    }

    public void setCreatedData(Date createdData) {
        this.createdData = createdData;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Boolean getIsNotActive() {
        return isNotActive;
    }

    public void setIsNotActive(Boolean notActive) {
        isNotActive = notActive;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", roleId=" + roleId +
                ", isNotActive=" + isNotActive +
                '}';
    }
}
