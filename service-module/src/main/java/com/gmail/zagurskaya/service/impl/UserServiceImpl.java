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

import java.time.LocalDate;
import java.util.List;

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
        return null;
    }

    @Override
    @Transactional
    public void add(UserDTO userDTO) {
        LocalDate date = LocalDate.now();
        User user = userConverter.toEntity(userDTO);
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        user.setCreatedData(java.sql.Date.valueOf(date));
        userRepository.save(user);
    }

//    @Override
//    @Transactional
//    public void delete(Long id) {
//        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id" + id));
//        userRepository.deleteById(user.getId());
//    }
//
//    @Override
//    @Transactional
//    public void update(UserDTO userDTO) {
//        userRepository.findById(userDTO.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found with id" + userDTO.getId()));
//        User user = userConverter.toEntity(userDTO);
//        userRepository.save(user);
//    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO loadUserByUsername(String name) {
        User loaded = userRepository.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("User not found with userName" + name));
        return userConverter.toDTO(loaded);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public UserDTO getById(Long id) {
//        User loaded = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id" + id));
//        return userConverter.toDTO(loaded);
//    }

//    @Override
//    @Transactional(readOnly = true)
//    public UserDTO findByUsername(String username) {
////    public UserDTO findByUsername(String username) {
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//        return userConverter.toDTO(user);
//    }

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


}
