package com.codecool.dealership.controller;
import com.codecool.dealership.dto.CarDto;
import com.codecool.dealership.dto.CustomerDto;
import com.codecool.dealership.model.Customer;
import com.codecool.dealership.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping
    public ResponseEntity<?> getCustomers() {
        List<CustomerDto> customerDto = customerService.getCustomers();
        return ResponseEntity.ok(customerDto);
    }

    @GetMapping("/{id}/cars")
    public ResponseEntity<?> getAllCustomersOfACustomer(@PathVariable Long id) {
        try {
            List<CarDto> carDto = customerService.getAllCarsOfACustomer(id);
            return ResponseEntity.ok(carDto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid Customer customer, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customerService.createAndUpdateCustomer(customer));
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Valid Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(customerService.createAndUpdateCustomer(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id) {
        if (customerService.deleteCustomer(id).equals("Customer successfully deleted")) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
