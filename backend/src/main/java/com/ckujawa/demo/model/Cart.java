package com.ckujawa.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Trip> cartContents = new ArrayList<>();


    public void addTrip(Trip trip) {

        cartContents.add(trip);
    }

    public void removeTrip(Long tripId){
        cartContents = cartContents.stream()
                .filter(p -> !p.getId().equals(tripId))
                .collect(Collectors.toList());
    }

}
