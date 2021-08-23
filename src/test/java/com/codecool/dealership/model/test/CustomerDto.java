package com.codecool.dealership.model.test;

import com.codecool.dealership.model.Car;
import com.codecool.dealership.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String placeOfBirth;
    private String nationality;
    private Date dateOfBirth;
    private List<Long> carList;

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
