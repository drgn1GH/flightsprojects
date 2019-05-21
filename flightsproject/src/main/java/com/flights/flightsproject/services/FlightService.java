package com.flights.flightsproject.services;

import com.flights.flightsproject.model.Flight;
import com.flights.flightsproject.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getFlights(){
        return flightRepository.findAllByOrderByIdAsc();
    }

    public Flight getFlightById(String id) {
        return flightRepository.getOne(Long.valueOf(id));
    }

    public Flight updateFlight(Flight flight) {
        if (flight == null) {
            return null;
        }

        if (flightRepository.findOneById(flight.getId()).isPresent()){
            return flightRepository.save(flight);
        }
        return null;
    }


    //niste not null de setat si dest != depa
    public Flight createFlight(Flight flightToBeCreated) {
        if(!flightRepository.findOneByName(flightToBeCreated.getName()).isPresent()){
            return flightRepository.save(flightToBeCreated);
        }
        return null;
    }


    public boolean deleteFlight(String id){
        Optional<Flight> flight = flightRepository.findOneById(Long.valueOf(id));

        if (flight.isPresent()){
            flightRepository.delete(flight.get());
            return true;
        }

        return false;
    }
}
