package com.codecool.dealership.service;
import com.codecool.dealership.dto.CarDto;
import com.codecool.dealership.dto.CustomerDto;
import com.codecool.dealership.dto.CustomerDto;
import com.codecool.dealership.model.Car;
import com.codecool.dealership.model.Customer;
import com.codecool.dealership.model.Customer;
import com.codecool.dealership.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream().map(CustomerDto::new).collect(Collectors.toList());
    }

    public CustomerDto createAndUpdateCustomer(Customer customer) {
        return new CustomerDto(customerRepository.save(customer));    }

    public String deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return "Customer successfully deleted";
        } else {
            return "Customer not found";
        }
    }

    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return null;
        }
        return new CustomerDto(customer);
    }

    public List<CarDto> getAllCarsOfACustomer(Long id) {
        if (customerRepository.existsById(id)) {
            return customerRepository.getById(id).getCarList().stream().map(CarDto::new).collect(Collectors.toList());
        } else {
            throw new RuntimeException("Customer is not found");
        }
    }
}
