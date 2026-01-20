package com.example.rentcarsportalee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String name;
    private String surname;
    private String licenseNumber;
    private String phone;
    private String email;
}
