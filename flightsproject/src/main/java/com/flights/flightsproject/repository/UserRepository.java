package com.flights.flightsproject.repository;

import com.flights.flightsproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByIdAsc();

    Optional<User> findOneByUsername(String username);

    Optional<User> findOneByEmail(String email);
}
