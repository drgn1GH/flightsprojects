package com.flights.flightsproject.controllers;

import com.flights.flightsproject.model.Flight;
import com.flights.flightsproject.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping(value = "/flights")
    public List<Flight> getFlights() {
        return flightService.getFlights();
    }


    @GetMapping(value = "/flights/{id}")
    public ResponseEntity<Flight> getFlightByID(@PathVariable String id) {
        Optional<Flight> byId = flightService.getFlightById(id);

        if(byId.isPresent()){
            return new ResponseEntity<>(byId.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PutMapping(value = "/flights")
    public ResponseEntity<Flight> updateFlight(@RequestBody Flight flight) {

        Flight flightUpdated = flightService.updateFlight(flight);

        if (flightUpdated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(flightUpdated,HttpStatus.ACCEPTED);
    }

    @PostMapping(value= "/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
        if(flight == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Flight flightCreated = flightService.createFlight(flight);

        if (flightCreated == null){
            return new ResponseEntity<>(flightCreated,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(flightCreated,HttpStatus.ACCEPTED);
    }

    @PostMapping(value= "/flights/delete/{id}")
    public ResponseEntity deleteFlight(@PathVariable String id){
        boolean deleted = flightService.deleteFlight(id);

        if(deleted){
            return new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return  new ResponseEntity(HttpStatus.NOT_FOUND);

    }
}
