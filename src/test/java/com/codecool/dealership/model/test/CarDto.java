package com.codecool.dealership.model.test;

import com.codecool.dealership.model.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarDto {

    private Long id;
    private String modelType;
    private Long serialNumber;
    private Long configuration;
    private Long customer;

    public CarDto(Car car) {
        id = car.getId();
        modelType = car.getModelType();
        serialNumber = car.getSerialNumber();
        configuration = car.getConfiguration().getId();
        customer = car.getCustomer().getId();
    }
}
