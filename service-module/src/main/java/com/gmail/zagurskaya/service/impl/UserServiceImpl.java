package com.gmail.zagurskaya.service.impl;

import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.UserService;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

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
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(UserDTO userDTO) {
        User user = userConverter.toEntity(userDTO);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO loadUserByUsername(String name) {
        User loaded = userRepository.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("User not found with userName" + name));
        return userConverter.toDTO(loaded);
    }

    @Override
    public Long findUserIdByUsername(String name) {
        Optional<User> user = userRepository.findByUsername(name);
        return user.isPresent() ? user.get().getId() : 0L;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void changePassword(String mail, String newPassword) {
        User user = userRepository.findByEmail(mail).orElseThrow(() -> new EntityNotFoundException("User not found with email" + mail));
        String password = passwordEncoder.encode(newPassword);
        user.setPassword(password);
        userRepository.save(user);
    }

}
