package com.flights.flightsproject.controllers;

import com.flights.flightsproject.model.Reservation;
import com.flights.flightsproject.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservations/{username}")
    public ResponseEntity<Reservation> createReservation(@PathVariable String username, @RequestBody Reservation reservation) {
        Optional<Reservation> created = reservationService.createReservation(username, reservation);

        if (created.isPresent()) {
            return new ResponseEntity<>(created.get(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/reservations/{username}")
    public ResponseEntity getReservation(@PathVariable String username) {
        List<Reservation> reservationByUser = reservationService.findReservationByUser(username);
        return new ResponseEntity<>(reservationByUser, HttpStatus.OK);
    }
}
