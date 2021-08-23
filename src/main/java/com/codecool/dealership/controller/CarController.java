package com.codecool.dealership.controller;
import com.codecool.dealership.dto.CarDto;
import com.codecool.dealership.model.Car;
import com.codecool.dealership.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable Long id) {
         CarDto carDto = carService.getCarById(id);
        if (carDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carDto);
    }

    @GetMapping
    public ResponseEntity<?> getCars() {
        List<CarDto> carDto = carService.retrieveCars();
        return ResponseEntity.ok(carDto);
    }

    @PutMapping
    public ResponseEntity<?> updateCar(@RequestBody @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(carService.createAndUpdateCar(car));
    }

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(carService.createAndUpdateCar(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {
            if (carService.deleteCar(id).equals("Car successfully deleted")) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
    }
}
