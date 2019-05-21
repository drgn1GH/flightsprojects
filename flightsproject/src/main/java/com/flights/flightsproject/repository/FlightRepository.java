package com.flights.flightsproject.repository;

import com.flights.flightsproject.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findAllByOrderByIdAsc();

    Optional<Flight> findOneById(Long id);

    Optional<Flight> findOneByName(String name);

}
