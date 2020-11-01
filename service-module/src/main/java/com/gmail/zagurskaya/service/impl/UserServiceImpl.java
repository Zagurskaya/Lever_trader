package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserConverter userConverter, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll(0, Integer.MAX_VALUE);
        List<UserDTO> dtos = users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    @Transactional
    public void add(UserDTO userDTO) {
        LocalDate date = LocalDate.now();
        User user = userConverter.toEntity(userDTO);
        String password = encoder(userDTO.getPassword());
        user.setPassword(password);
        user.setCreatedData(java.sql.Date.valueOf(date));
        userRepository.persist(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id);
        userRepository.remove(user);
    }

    @Override
    @Transactional
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) {
        User user = userConverter.toEntity(userDTO);
        userRepository.merge(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO loadUserByUsername(String name) {
        User loaded = userRepository.loadUserByUsername(name);
        UserDTO userDTO = userConverter.toDTO(loaded);
        return userDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        User loaded = (User) userRepository.findById(id);
        UserDTO userDTO = userConverter.toDTO(loaded);
        return userDTO;
    }

    private String encoder(String word) {
        String encode = passwordEncoder.encode(word);
        return encode;

    }

}
