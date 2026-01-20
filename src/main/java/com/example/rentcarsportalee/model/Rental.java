package com.example.rentcarsportalee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    private int id;
    private Car car;
    private Customer customer;
    private Date startDate;
    private Date endDate;
    private double totalCost;
    private RentalStatus rentalStatus;
}
