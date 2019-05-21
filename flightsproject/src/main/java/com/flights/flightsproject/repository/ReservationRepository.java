package com.flights.flightsproject.repository;

import com.flights.flightsproject.model.Reservation;
import com.flights.flightsproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByUser(User user);
}
