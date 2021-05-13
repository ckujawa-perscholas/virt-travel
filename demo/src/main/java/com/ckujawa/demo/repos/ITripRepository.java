package com.ckujawa.demo.repos;

import com.ckujawa.demo.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ITripRepository extends MongoRepository<Trip, Long> {

    List<Trip> findByDestination(String destination);

    Trip findByTripName(String tripName);
}
