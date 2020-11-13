package com.gmail.zagurskaya.service.converter;

import com.gmail.zagurskaya.repository.model.RoleEnum;
import com.gmail.zagurskaya.repository.model.User;
import com.gmail.zagurskaya.service.converter.impl.UserConverterImpl;
import com.gmail.zagurskaya.service.model.UserDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {
    private UserConverter userConverter;
    private User user;

    @Before
    public void init() {
        userConverter = new UserConverterImpl();
        user = new User();
        user.setRole(RoleEnum.GUEST);
    }

    @Test
    public void shouldReturnUserDTOWithSameID() {
        user.setId(1L);
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getId(), userDTO.getId());
    }

    @Test
    public void shouldReturnUserDTOWithSameEmail() {
        user.setEmail("email@test.com");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getEmail(), userDTO.getEmail());
    }

    @Test
    public void shouldReturnUserDTOWithSameUserName() {
        user.setUsername("name");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getUsername(), userDTO.getUsername());
    }

    @Test
    public void shouldReturnUserDTOWithSameSurname() {
        user.setFirstName("firstName");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getFirstName(), userDTO.getFirstName());
    }

    @Test
    public void shouldReturnUserDTOWithSameLastName() {
        user.setLastName("lastName");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getLastName(), userDTO.getLastName());
    }

    @Test
    public void shouldReturnUserDTOWithSamePassword() {
        user.setPassword("password");
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getPassword(), userDTO.getPassword());
    }

    @Test
    public void shouldReturnUserDTOWithSameNowDate() {
        user.setCreatedData(LocalDate.now());
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getCreatedData(), userDTO.getCreatedData());
    }

    @Test
    public void shouldReturnUserDTOWithSameRole() {
        user.setRole(RoleEnum.ADMIN);
        UserDTO userDTO = userConverter.toDTO(user);
        assertEquals(user.getRole().name(), userDTO.getRole());
    }
}
