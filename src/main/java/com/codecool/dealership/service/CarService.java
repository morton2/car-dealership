package com.codecool.dealership.service;
import com.codecool.dealership.dto.CarDto;
import com.codecool.dealership.model.Car;
import com.codecool.dealership.repository.CarRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarDto getCarById(Long id) {
        Car car = carRepository.findById(id).orElse(null);
        if (car == null) {
            return null;
        }
        return new CarDto(car);
    }

    public List<CarDto> retrieveCars() {
        return carRepository.findAll().stream().map(CarDto::new).collect(Collectors.toList());
    }

    public CarDto createAndUpdateCar(Car car) {
        return new CarDto(carRepository.save(car));
    }

    public String deleteCar(Long id) {
        if (carRepository.existsById(id)) {
           carRepository.deleteById(id);
           return "Car successfully deleted";
        } else {
            return "Car not found";
        }
    }

}
