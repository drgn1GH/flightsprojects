package com.flights.flightsproject.services;

import com.flights.flightsproject.model.User;
import com.flights.flightsproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAllByOrderByIdAsc();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(Long.valueOf(id));
    }

    public User updateUser(User user) {
        if (user == null) {
            return null;
        }

        if (userRepository.findById(user.getId()).isPresent()) {
            return userRepository.save(user);
        }
        return null;
    }

    public User createUser(User userToBeCreated) {
        if (!userRepository.findOneByUsername(userToBeCreated.getUsername()).isPresent()) {
            return userRepository.save(userToBeCreated);
        }

        return null;
    }

    public boolean deleteUser(String id) {
        Optional<User> user = userRepository.findById(Long.valueOf(id));

        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        }
        return false;
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }
}
