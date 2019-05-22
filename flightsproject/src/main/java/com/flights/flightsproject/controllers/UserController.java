package com.flights.flightsproject.controllers;

import com.flights.flightsproject.model.User;
import com.flights.flightsproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable String id){
        Optional<User> byId = userService.getUserById(id);

        if(byId.isPresent()){
            return new ResponseEntity<>(byId.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/users/username/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable String username) {
        Optional<User> byUsername = userService.getByUsername(username);

        if (byUsername.isPresent()) {
            return new ResponseEntity<>(byUsername.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        User userUpdated = userService.updateUser(user);

        if (userUpdated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userUpdated, HttpStatus.ACCEPTED);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User userCreated = userService.createUser(user);

        if (userCreated == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userCreated,HttpStatus.ACCEPTED);
    }

    @PostMapping("/users/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        boolean deleted = userService.deleteUser(id);

        if (deleted) {
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
