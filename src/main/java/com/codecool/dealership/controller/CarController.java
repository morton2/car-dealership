package com.codecool.dealership.controller;
import com.codecool.dealership.model.Car;
import com.codecool.dealership.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> retrieveCars() {
        return carService.retrieveCars();
    }

    @PutMapping
    public Car updateCar(@RequestBody Car car){
        return carService.updateCar(car);
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@RequestParam("id") Long id) {
        carService.deleteCar(id);
    }
}
