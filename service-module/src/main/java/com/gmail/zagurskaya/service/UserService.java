package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.service.model.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getUsers();

    void add(UserDTO user);

    UserDTO loadUserByUsername(String name);

    Long findUserIdByUsername(String name);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void changePassword(String mail, String newPassword);
}
