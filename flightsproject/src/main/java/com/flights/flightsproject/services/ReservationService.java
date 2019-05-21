package com.flights.flightsproject.services;

import com.flights.flightsproject.model.Reservation;
import com.flights.flightsproject.model.User;
import com.flights.flightsproject.repository.ReservationRepository;
import com.flights.flightsproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<Reservation> createReservation(String username, Reservation reservation) {
        Optional<User> user = userRepository.findOneByUsername(username);

        if (!user.isPresent() || reservation == null) {
            return Optional.empty();
        }

        List<Reservation> reservationByUser = findReservationByUser(username);

        for (Reservation r : reservationByUser) {
            if (r.getFlight().equals(reservation.getFlight())) {
                return Optional.empty();
            }
        }

        reservation.setUser(user.get());
        return Optional.of(reservationRepository.save(reservation));

    }

    public List<Reservation> findReservationByUser(String username) {
        Optional<User> user = userRepository.findOneByUsername(username);

        if (user.isPresent()) {
            return reservationRepository.findAllByUser(user.get());
        }

        return new ArrayList<>();
    }
}
