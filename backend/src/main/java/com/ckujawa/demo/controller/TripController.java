package com.ckujawa.demo.controller;

import com.ckujawa.demo.model.Trip;
import com.ckujawa.demo.repos.ITripRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/trips")
@Api("API Calls to manage trips")
public class TripController {
    
    @Autowired
    private ITripRepository repo;

    @ApiOperation(value = "Get a list of available trips")
    @GetMapping()
    public ResponseEntity<?> getAvailableTrips() {
        log.debug("getting all trips...");
        try {
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("An error occurred when retrieving all available trips: " + e.getMessage(), e);
            return new ResponseEntity<>("Error while retrieving all available trips", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Add a trip to the database")
    @PostMapping
    public ResponseEntity<?> addTrip(@Valid @RequestBody Trip trip) {
        log.debug("Adding a new trip <" + trip.toString() + ">");
        try {
            return new ResponseEntity<>(repo.save(trip), HttpStatus.OK);
        } catch (Exception e) {
            log.error("An error occurred when saving a new trip: " + e.getMessage(), e);
            return new ResponseEntity<>("Something went wrong when saving the new trip", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update the number of seats for trip <tripId>. numberOfSeats should be the new nubmer of seats available.")
    @PutMapping("/{tripId}/{numberOfSeats}")
    public ResponseEntity<?> updateTrip(@PathVariable Long tripId, @PathVariable Integer numberOfSeats) {
        log.debug("The trip to update is <" + tripId + "> and the new number of seats is <" + numberOfSeats + ">");
        try {
            Optional<Trip> toUpdate = repo.findById(tripId);
            if (toUpdate.isPresent()) {
                log.debug("Found the trip to update...update in progress...");
                Trip trip = toUpdate.get();
                trip.setSeatsAvailable(numberOfSeats);
                log.debug("update complete...returning the updated trip with numberOfSeats: <" + trip.getSeatsAvailable() + ">");
                return new ResponseEntity<>(repo.save(trip), HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unable to find trip with id: <" + tripId + ">", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.error("Something went wrong when updating trip with id <" + tripId + "> The error message was: " + e.getMessage(), e);
            return new ResponseEntity<>("An error occurred when updating the seats available on the trip.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Delete trip <tripId>")
    @DeleteMapping("/{tripId}")
    public ResponseEntity<?> deleteTrip(@PathVariable Long tripId) {
        log.debug("The trip id to delete is: <" + tripId + ">");
        try {

            Optional<Trip> toDelete = repo.findById(tripId);
            if (toDelete.isPresent()) {
                Trip trip = toDelete.get();
                log.debug("Trip <" + tripId + "> was found...deleting it now");
                repo.delete(trip);
            }
            log.debug("Trip <" + tripId + "> was deleted...returning a happy message");
            return new ResponseEntity<>("Your trip was deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.error("An exception was thrown when trying to delete trip with id <" + tripId + ">: " + e.getMessage(), e);
            return new ResponseEntity<>("Unable to delete your trip.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
