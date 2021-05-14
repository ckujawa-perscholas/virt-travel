package com.ckujawa.demo.repos;

import com.ckujawa.demo.model.Trip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ITripRepository extends CrudRepository<Trip, Long> {

    List<Trip> findByDestination(String destination);

    Trip findByTripName(String tripName);
}
