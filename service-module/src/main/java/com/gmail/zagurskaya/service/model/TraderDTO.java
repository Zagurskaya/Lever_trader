package com.gmail.zagurskaya.service.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TraderDTO {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    //    @NotNull
    private Boolean isNotActive;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsNotActive() {
        return isNotActive;
    }

    public void setIsNotActive(Boolean notActive) {
        isNotActive = notActive;
    }

    @Override
    public String toString() {
        return "TraderDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isNotActive=" + isNotActive +
                '}';
    }
}
