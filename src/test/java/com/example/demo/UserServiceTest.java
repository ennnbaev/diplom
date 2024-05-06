package com.example.demo;


import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserInfoDto;
import com.example.demo.user.mapper.UserMapper;
import com.example.demo.user.repo.UserRepository;
import com.example.demo.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUserInfo_Success() throws Exception {
        // Arrange
        String username = "testUser";
        UserInfoDto userInfoDto = new UserInfoDto();
        User user = new User();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(userInfoDto);

        // Act
        UserInfoDto result = userService.getUserInfo(username);

        // Assert
        assertNotNull(result);
        assertEquals(userInfoDto, result);
    }

    @Test
    void deleteUserAccount_Success() {
        // Arrange
        String userId = "1";

        // Act
        userService.deleteUserAccount(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(Long.parseLong(userId));
    }


    @Test
    void updateUserInfo_Success() throws Exception {
        // Arrange
        String username = "testUser";
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUsername("newUsername");
        userInfoDto.setEmail("newEmail@example.com");
        userInfoDto.setPhoneNumber("1234567890");
        User userFromDB = new User();

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(userFromDB));

        // Act
        UserInfoDto result = userService.updateUser(username, userInfoDto);

        // Assert
        assertEquals(userInfoDto.getUsername(), userFromDB.getUsername());
        assertEquals(userInfoDto.getEmail(), userFromDB.getEmail());
        assertEquals(userInfoDto.getPhoneNumber(), userFromDB.getPhoneNumber());
        assertEquals(userInfoDto, result);
        verify(userRepository, times(1)).save(userFromDB);
    }
}

