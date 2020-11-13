package com.gmail.zagurskaya.service;

import com.gmail.zagurskaya.repository.UserRepository;
import com.gmail.zagurskaya.repository.model.RoleEnum;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.converter.UserConverter;
import com.gmail.zagurskaya.service.impl.UserServiceImpl;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserConverter userConverter;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @Before
    public void init() {
        userService = new UserServiceImpl(userConverter, userRepository, passwordEncoder);
    }

    @Test
    public void shouldGetUserDTOListForGetUsersMethodCall() {
        User user = new User();
        user.setRole(RoleEnum.GUEST);
        UserDTO userDTO = new UserDTO();
        userDTO.setRole("GUEST");
        List<User> users = Collections.singletonList(user);
        when(userRepository.findAll()).thenReturn(users);
        when(userConverter.toDTO(user)).thenReturn(userDTO);
        List<UserDTO> returnedUsers = userService.getUsers();
        assertEquals(userDTO, returnedUsers.get(0));
    }

    @Test
    public void shouldExecuteMethodAddUserWithoutExceptions() {
        User user = new User();
        user.setRole(RoleEnum.GUEST);
        UserDTO userDTO = new UserDTO();
        userDTO.setRole("GUEST");
        when(userConverter.toEntity(userDTO)).thenReturn(user);
        when(passwordEncoder.encode(any())).thenReturn("password");
        userService.add(userDTO);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWhenCatchingExceptionFromPasswordEncoderForAddMethodCall() {
        User user = new User();
        user.setRole(RoleEnum.GUEST);
        UserDTO userDTO = new UserDTO();
        userDTO.setRole("GUEST");
        when(userConverter.toEntity(userDTO)).thenReturn(user);
        when(passwordEncoder.encode(any())).thenThrow(new RuntimeException());
        userService.add(userDTO);
    }

    @Test
    public void shouldGetUserDTOForLoadUserByUsernameMethodCall() {
        String userName = "userName";
        Optional<User> user = Optional.of(new User());
        user.get().setRole(RoleEnum.TRADER);
        UserDTO userDTO = new UserDTO();
        userDTO.setRole("TRADER");
        when(userRepository.findByUsername(userName)).thenReturn(user);
        when(userConverter.toDTO(user.get())).thenReturn(userDTO);
        UserDTO userByUsername = userService.loadUserByUsername(userName);
        assertEquals(userDTO, userByUsername);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowUsernameNotFoundExceptionWhenCatchingExceptionFromRepositoryForGetUserByUsernameMethodCall() {
        String userName = "userName";
        when(userRepository.findByUsername(userName)).thenThrow(new UsernameNotFoundException("User not found with userName"));
        userService.loadUserByUsername(userName);
    }

    @Test
    public void shouldGetUserDTOForFindUserIdByUsernameMethodCall() {
        String userName = "userName";
        User user = new User();
        user.setId(1L);
        when(userRepository.findByUsername(userName)).thenReturn(Optional.of(user));
        Long userId = userService.findUserIdByUsername(userName);
//        assertEquals(1, userId);
    }

//    @Test
//    public void shouldGet0WhenFromRepositoryForFindUserIdByUsernameMethodCall() {
//        String userName = "userName";
//        when(userRepository.findByUsername(userName)).thenReturn(Optional.of(null));
//        Long userId = userService.findUserIdByUsername(userName);
////        assertEquals(1, userId);
//    }

    @Test
    public void shouldGetTrueForExistsByUsernameMethodCall() {
        String userName = "userName";
        when(userRepository.existsByUsername(userName)).thenReturn(true);
        boolean result = userService.existsByUsername(userName);
        assertEquals(true, result);
    }

    @Test
    public void shouldGetFalseForExistsByUsernameMethodCall() {
        String userName = "userName";
        when(userRepository.existsByUsername(userName)).thenReturn(false);
        boolean result = userService.existsByUsername(userName);
        assertEquals(false, result);
    }

    @Test
    public void shouldGetTrueForExistsByEmailMethodCall() {
        String email = "Email";
        when(userRepository.existsByEmail(email)).thenReturn(true);
        boolean result = userService.existsByEmail(email);
        assertEquals(true, result);
    }

    @Test
    public void shouldGetFalseForExistsByEmailMethodCall() {
        String email = "Email";
        when(userRepository.existsByEmail(email)).thenReturn(false);
        boolean result = userService.existsByEmail(email);
        assertEquals(false, result);
    }


    @Test
    public void shouldExecuteMethodChangePasswordWithoutExceptions() {
        String email = "email";
        String password = "password";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));
        when(passwordEncoder.encode(password)).thenReturn(password);
        userService.changePassword(email, password);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionMethodChangePasswordWithoutExceptions() {
        String email = "email";
        String password = "password";
        when(userRepository.findByEmail(email)).thenThrow(new EntityNotFoundException("User not found with userName"));
        userService.changePassword(email, password);
    }
}
