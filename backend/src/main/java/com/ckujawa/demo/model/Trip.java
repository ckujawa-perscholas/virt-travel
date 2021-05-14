package com.ckujawa.demo.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Data //let Lombok generate out accessor methods...
@ToString //another beautiful thing from Lombok...
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Trip name is mandatory")
    private String tripName;

    @NotBlank(message = "What's the point in a trip if you're not going anywhere?")
    private String destination;

    @NotBlank(message = "Is this place really so bad you don't want it on display???")
    private String imageUrl;

    @Min(500)
    @Max(100000)
    private BigDecimal cost;

    @Min(0)
    @Max(150)
    private Integer seatsAvailable;
}
