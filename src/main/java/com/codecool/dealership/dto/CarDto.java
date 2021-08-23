package com.codecool.dealership.dto;

import com.codecool.dealership.model.Car;
import com.codecool.dealership.model.Configuration;
import com.codecool.dealership.model.Customer;
import lombok.Getter;

import javax.persistence.ManyToOne;
import java.util.List;

@Getter
public class CarDto {

    private final Long id;
    private final String modelType;
    private final Long serialNumber;
    private final Long configuration;
    private final Long customer;

    public CarDto(Car car) {
        id = car.getId();
        modelType = car.getModelType();
        serialNumber = car.getSerialNumber();
        configuration = car.getConfiguration() == null ? 0L : car.getConfiguration().getId();
        customer = car.getConfiguration() == null ? 0L : car.getCustomer().getId();
    }
}
