package com.codecool.dealership.integration.test;

import com.codecool.dealership.model.Car;
import com.codecool.dealership.model.Customer;
import com.codecool.dealership.model.test.CarDto;
import com.codecool.dealership.model.test.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class CustomerTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUrl() {
        this.baseUrl = "http://localhost:" + port + "/customer";
    }

    @Test
    public void addCustomer_returnSameCustomer() throws ParseException {
        Customer testCustomer = new Customer(null, "Frodo", "Hobbiton", "Hobbit", new SimpleDateFormat("yyyy/MM/dd").parse("2001/01/01"), null);
        CustomerDto result = testRestTemplate.postForObject(baseUrl, testCustomer, CustomerDto.class);
        assertEquals(testCustomer.getName(), result.getName());
    }

    @Test
    public void getCustomer_returnEmptyList() {
        List<CustomerDto> customerList = List.of(testRestTemplate.getForObject(baseUrl, CustomerDto[].class));
        assertEquals(0, customerList.size());
    }

    @Test
    public void getCustomerById_returnCustomerWithTheSameId() throws ParseException {
        Customer testCustomer = new Customer(null, "Frodo", "Hobbiton", "Hobbit", new SimpleDateFormat("yyyy/MM/dd").parse("2001/01/01"), null);
        CustomerDto testCustomerResult = testRestTemplate.postForObject(baseUrl, testCustomer, CustomerDto.class);
        CustomerDto result = testRestTemplate.getForObject(baseUrl + "/" + testCustomerResult.getId(), CustomerDto.class);
        assertEquals(testCustomerResult.getId(), result.getId());
    }

    @Test
    public void updateCustomer_returnWithUpdatedModel() throws ParseException {
        Customer testCustomer = new Customer(null, "Frodo", "Hobbiton", "Hobbit", new SimpleDateFormat("yyyy/MM/dd").parse("2001/01/01"), null);
        CustomerDto result = testRestTemplate.postForObject(baseUrl, testCustomer, CustomerDto.class);
        Customer testCustomerModified = new Customer(result.getId(), "Sam", "Hobbiton", "Hobbit", new SimpleDateFormat("yyyy/MM/dd").parse("2001/01/01"), null);
        testRestTemplate.put(baseUrl, testCustomerModified);
        CustomerDto resultModified = testRestTemplate.getForObject(baseUrl+"/"+ result.getId(),CustomerDto.class);
        assertEquals("Sam", resultModified.getName());
    }

    @Test
    public void deleteCustomer_returnWithLeftCustomers() throws ParseException {
        Customer testCustomer = new Customer(null, "Joseph", "Hobbiton", "Hobbit", new SimpleDateFormat("yyyy/MM/dd").parse("2001/01/01"), null);
        Customer testCustomer2 = new Customer(null, "Sam", "Hobbiton", "Hobbit", new SimpleDateFormat("yyyy/MM/dd").parse("2001/01/01"), null);
        CustomerDto result = testRestTemplate.postForObject(baseUrl, testCustomer, CustomerDto.class);
        CustomerDto result2 = testRestTemplate.postForObject(baseUrl, testCustomer2, CustomerDto.class);

        testRestTemplate.delete(baseUrl + "/" + testCustomer.getId());

        List<CustomerDto> leftCustomers = List.of(testRestTemplate.getForObject(baseUrl, CustomerDto[].class));

        assertEquals(result2.getName(),leftCustomers.get(1).getName());
    }

    @Test
    public void getCarsByCustomerId_returnCarsListSize() throws ParseException {
        Customer testCustomer = new Customer(null, "Joseph", "Hobbiton", "Hobbit", new SimpleDateFormat("yyyy/MM/dd").parse("2001/01/01"), null);
        CustomerDto result = testRestTemplate.postForObject(baseUrl, testCustomer, CustomerDto.class);
        testCustomer.setId(result.getId());
        Car car = new Car(null,"first",123467L,null,testCustomer);
        Car car1 = new Car(null,"second",123467L,null,testCustomer);
        Car car2 = new Car(null,"third",123467L,null,testCustomer);
        testRestTemplate.postForObject("http://localhost:" + port + "/car", car, CustomerDto.class);
        testRestTemplate.postForObject("http://localhost:" + port + "/car", car1, CustomerDto.class);
        testRestTemplate.postForObject("http://localhost:" + port + "/car", car2, CustomerDto.class);
        List<CarDto> carList = List.of(testRestTemplate.getForObject(baseUrl+"/"+result.getId()+"/cars", CarDto[].class));
        assertEquals(3,carList.size());
    }

}