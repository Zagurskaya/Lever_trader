package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getUsers();

    void add(UserDTO user);

//    void delete(Long id);

    UserDTO loadUserByUsername(String name);

//    UserDTO getUserById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
