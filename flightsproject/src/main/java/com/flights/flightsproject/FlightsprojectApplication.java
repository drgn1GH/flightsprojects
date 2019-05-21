package com.flights.flightsproject;

import com.flights.flightsproject.model.*;
import com.flights.flightsproject.repository.FlightRepository;
import com.flights.flightsproject.repository.UserRepository;
import com.flights.flightsproject.services.FlightService;
import com.flights.flightsproject.services.ReservationService;
import com.flights.flightsproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
@EntityScan
public class FlightsprojectApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private ReservationService reservationService;

	public static void main(String[] args) {
		SpringApplication.run(FlightsprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*User user = new User();

		user.setUsername("mario");
		user.setPassword("123");

		User user1 = new User();

		user1.setUsername("user1");
		user1.setPassword("123");

//		userService.createUser(user);
		userRepository.save(user);
		userRepository.save(user1);

		*/

		Flight flight = new Flight();

		flight.setName("LALALA");
		flight.setPrice(new BigDecimal(23.34));
		flight.setDeparture(Departure.CLUJ);
		flight.setDestination(Destination.MADRID);


		flightRepository.save(flight);

		Flight flight1 = flightRepository.findOneById(1L).get();

		Reservation reservation = new Reservation();

		reservation.setDateTime(new Date());
		reservation.setFlight(flight1);
		reservationService.createReservation("andrei", reservation);
	}
}
