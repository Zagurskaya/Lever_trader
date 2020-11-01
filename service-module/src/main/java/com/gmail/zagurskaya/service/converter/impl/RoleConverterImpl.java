package com.gmail.zagurskaya.service.converter.impl;

import com.gmail.zagurskaya.repository.model.Role;
import com.gmail.zagurskaya.repository.model.RoleEnum;
import com.gmail.zagurskaya.service.converter.RoleConverter;
import com.gmail.zagurskaya.service.model.RoleDTO;
import com.gmail.zagurskaya.service.model.RoleNameDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleConverterImpl implements RoleConverter {

    @Override
    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(RoleNameDTO.valueOf(role.getName().name()));
        return roleDTO;
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(RoleEnum.valueOf(roleDTO.getName().name()));
        return role;
    }
}
