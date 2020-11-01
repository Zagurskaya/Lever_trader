package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.RoleDTO;
import com.gmail.zagurskaya.service.model.RoleNameDTO;

public interface RoleService {

    RoleDTO findByName(RoleNameDTO roleName);
}
