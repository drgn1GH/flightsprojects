package com.flights.flightsproject.services;

import com.flights.flightsproject.model.Departure;
import com.flights.flightsproject.model.Destination;
import com.flights.flightsproject.model.Flight;
import com.flights.flightsproject.repository.FlightRepository;
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
public class FlightServiceTest {
    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    private Flight flight;


    @Before
    public void setUp() throws Exception {
        flight = new Flight();

        flight.setId(3L);

    }

    @Test
    public void getFlights() {
        when(flightRepository.findAllByOrderByIdAsc()).thenReturn(Collections.singletonList(flight));

        List<Flight> result = flightService.getFlights();

        assertFalse(result.isEmpty());

        verify(flightRepository,times(1)).findAllByOrderByIdAsc();
    }

    @Test
    public void getFlightById() {
        String id = "3";

        when(flightRepository.findById(Long.valueOf(id))).thenReturn(Optional.of(flight));

        Optional<Flight> result = flightService.getFlightById(id);

        assertTrue(result.isPresent());

        verify(flightRepository,times(1)).findById(Long.valueOf(id));


    }

    @Test
    public void updateFlight() {
        when(flightRepository.findById(flight.getId())).thenReturn((Optional.of(flight)));
        when(flightRepository.save(flight)).thenReturn(flight);

//        flight.setDeparture(Departure.CLUJ);
//        flight.setDestination(Destination.MADRID);
        flight.setName("dsda");
        flight.setDestination(Destination.ROMA);

        Flight updated = flightService.updateFlight(flight);

        assertNotNull(updated);

        verify(flightRepository,times(1)).save(flight);
    }

    @Test
    public void createFlight() {
        Flight flightToBeCreated = new Flight();

        flightToBeCreated.setDestination(Destination.ROMA);
        flightToBeCreated.setDeparture(Departure.CLUJ);

        when(flightRepository.save(flightToBeCreated)).thenReturn(flightToBeCreated);

        Flight successfullyCreated = flightService.createFlight(flightToBeCreated);

        assertNotNull(successfullyCreated);

        verify(flightRepository,times(1)).save(flightToBeCreated);
    }

    @Test
    public void deleteFlight() {
        when(flightRepository.findById(flight.getId())).thenReturn(Optional.of(flight));
        flightService.deleteFlight("3");

        verify(flightRepository,times(1)).delete(flight);
    }
}