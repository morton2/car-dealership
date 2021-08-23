package com.codecool.dealership.dto;

import com.codecool.dealership.model.Car;
import com.codecool.dealership.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomerDto {
    private final Long id;
    private final String name;
    private final String placeOfBirth;
    private final String nationality;
    private final Date dateOfBirth;
    private final List<Long> carList;

    public CustomerDto(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        placeOfBirth = customer.getPlaceOfBirth();
        nationality = customer.getNationality();
        dateOfBirth = customer.getDateOfBirth();
        carList = customer.getCarList() == null ? new ArrayList<>() : customer.getCarList()
                .stream().map(Car::getSerialNumber)
                .collect(Collectors.toList());;
    }
}
