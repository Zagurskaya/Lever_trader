package com.gmail.zagurskaya.service.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RoleDTO {

    private Long id;

    @NotBlank
    @Size(min = 4, max = 60)
    private RoleNameDTO name;

    public RoleDTO() {}

    public RoleDTO(RoleNameDTO name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleNameDTO getName() {
        return name;
    }

    public void setName(RoleNameDTO name) {
        this.name = name;
    }
}