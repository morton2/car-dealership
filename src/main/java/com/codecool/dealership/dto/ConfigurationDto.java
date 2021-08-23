package com.codecool.dealership.dto;

import com.codecool.dealership.model.Car;
import com.codecool.dealership.model.Configuration;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ConfigurationDto {
    private final Long id;
    private final Long trim;
    private final Long engine;
    private final Long interior;
    private final Long exterior;
    private final Long equipment;
    private final List<Long> cars;

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
