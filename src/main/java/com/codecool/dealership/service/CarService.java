package com.codecool.dealership.service;
import com.codecool.dealership.model.Car;
import com.codecool.dealership.repository.CarRepository;
import com.codecool.dealership.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;

    private CustomerRepository customerRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> retrieveCars() {
        return carRepository.findAll();
    }

    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }
}
