package com.nnk.springboot.service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.exeption.UserAlreadyExistingException;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    private static List<User> userList;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {

        userList = new ArrayList<>();
        userList.add(new User(1, "testusername1", "test test", "AAA BBB", "USER"));
        userList.add(new User(2, "testusername2", "test test", "CCC DDD", "USER"));
    }

    @Test
    public void should_Return_All_Users() {

        when(userRepository.findAll()).thenReturn(userList);
        userService.findAll();
        verify(userRepository).findAll();
    }

    @Test
    public void should_Save_User() throws UserAlreadyExistingException {

        User user = new User(1, "testusername1", "test test", "AAA BBB", "USER");
        user.setPassword("test");
        lenient().when(passwordEncoder.encode(anyString())).thenReturn("test");
        when(userRepository.save(user)).thenReturn(user);
        userService.save(user);
        verify(userRepository).save(user);
    }

    @Test
    public void save_Should_Throws_UserAlreadyExistingException() {

        User user = new User();
        user.setUsername("test");
        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        assertThrows(UserAlreadyExistingException.class, () -> userService.save(user));
    }

    @Test
    public void should_Return_User_By_Id() {

        User user = new User(1, "testusername1", "test test", "AAA BBB", "USER");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        userService.findById(1);
        verify(userRepository).findById(1);
    }

    @Test
    public void should_Delete_User_By_Id() {

        doNothing().when(userRepository).deleteById(1);
        userService.deleteById(1);
        verify(userRepository).deleteById(1);
    }

    @Test
    public void should_Update_User() throws UserAlreadyExistingException {

        User user = new User(1, "testusername1", "test test", "AAA BBB", "USER");
        lenient().when(userRepository.existsByUsername(anyString())).thenReturn(false);
        userService.update(user);
        verify(userRepository).save(user);
    }

    @Test
    public void update_Should_Throws_UserAlreadyExistingException() {
        User user1 = new User(1,"ghazi", "blabla", "AAA BBB","USER");
        User user2 = new User(2,"ghazi", "blabla", "AAA BBB","USER");
        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        Mockito.lenient().when(userRepository.findById(anyInt())).thenReturn(Optional.of(user1));
        assertThrows(UserAlreadyExistingException.class, () -> userService.update(user2));
    }

}
