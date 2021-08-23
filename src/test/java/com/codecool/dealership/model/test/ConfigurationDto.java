package com.codecool.dealership.model.test;

import com.codecool.dealership.model.Car;
import com.codecool.dealership.model.Configuration;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ConfigurationDto {
    private Long id;
    private Long trim;
    private Long engine;
    private Long interior;
    private Long exterior;
    private Long equipment;
    private List<Long> cars;

    public ConfigurationDto(Configuration configuration) {
        id = configuration.getId();
        trim = configuration.getTrim();
        engine = configuration.getEngine();
        interior = configuration.getInterior();
        exterior = configuration.getExterior();
        equipment = configuration.getEquipment();
        cars =  configuration.getCars() == null ? new ArrayList<>() : configuration.getCars()
                .stream().map(Car::getSerialNumber)
                .collect(Collectors.toList());
    }
}
