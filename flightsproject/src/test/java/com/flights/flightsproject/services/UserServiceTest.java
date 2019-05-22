package com.flights.flightsproject.services;

import com.flights.flightsproject.model.User;
import com.flights.flightsproject.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @Before
    public void setUp() throws Exception{
        user = new User();

        user.setId(1L);
    }

    @Test
    public void getUsers() {
        when(userRepository.findAllByOrderByIdAsc()).thenReturn(Collections.singletonList(user));

        List<User> result = userService.getUsers();

        assertFalse(result.isEmpty());

        verify(userRepository,times(1)).findAllByOrderByIdAsc();




    }

    @Test
    public void getUserById() {
        String id = "1";

        when(userRepository.findById(Long.valueOf(id))).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(id);

        assertTrue(result.isPresent());

        verify(userRepository,times(1)).findById(Long.valueOf(id));
    }

    @Test
    public void updateUser() {
        when(userRepository.findById(user.getId())).thenReturn((Optional.of(user)));
        when(userRepository.save(user)).thenReturn(user);

        user.setEmail("Nhoj@gmail.com");
        User updated = userService.updateUser(user);

        assertNotNull(updated);

        verify(userRepository,times(1)).save(user);

    }

    @Test
    public void createUser() {
        User userToBeCreated = new User();

        userToBeCreated.setEmail("John@gmail.com");
        userToBeCreated.setPassword("991");

        when(userRepository.save(userToBeCreated)).thenReturn(userToBeCreated);

        User successfullyCreated = userService.createUser(userToBeCreated);

        assertNotNull(successfullyCreated);

        verify(userRepository,times(1)).save(userToBeCreated);
    }

    @Test
    public void deleteUser() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        userService.deleteUser("1");

        verify(userRepository,times(1)).delete(user);
    }

    @Test
    public void getByUsername() {
        String username = "John";

        when(userRepository.findOneByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getByUsername(username);

        assertTrue(result.isPresent());

        verify(userRepository,times(1)).findOneByUsername(username);




    }
}