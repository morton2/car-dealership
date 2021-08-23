package com.codecool.dealership.integration.test;

import com.codecool.dealership.model.Car;
import com.codecool.dealership.model.test.CarDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
public class CarTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUrl() {
        this.baseUrl = "http://localhost:" + port + "/car";
    }

    @Test
    public void addCar_returnSameCar() {
        Car testCar = new Car(null,"sample_model",123467L,null,null);
        CarDto result = testRestTemplate.postForObject(baseUrl, testCar, CarDto.class);
        assertEquals(testCar.getModelType(), result.getModelType());
    }

    @Test
    public void getCar_returnEmptyList() {
        List<CarDto> carList = List.of(testRestTemplate.getForObject(baseUrl, CarDto[].class));
        assertEquals(0, carList.size());
    }

    @Test
    public void getCarById_returnCarWithTheSameId() {
        Car testCar = new Car(null,"sample_model",123467L,null,null);
        CarDto testCarResult = testRestTemplate.postForObject(baseUrl, testCar, CarDto.class);
        CarDto result = testRestTemplate.getForObject(baseUrl + "/" + testCarResult.getId(), CarDto.class);
        assertEquals(testCarResult.getId(), result.getId());
    }

    @Test
    public void updateCar_returnWithUpdatedModel() {
        Car testCar = new Car(null,"sample_model",123467L,null,null);
        CarDto result = testRestTemplate.postForObject(baseUrl, testCar, CarDto.class);
        Car testCarModified = new Car(result.getId(), "new_model",123467L,null,null);
        testRestTemplate.put(baseUrl, testCarModified);
        CarDto resultModified = testRestTemplate.getForObject(baseUrl+"/"+ result.getId(),CarDto.class);
        assertEquals("new_model", resultModified.getModelType());
    }

    @Test
    public void deleteCar_returnWithLeftCars() {
        Car testCar = new Car(null,"sample_model",123467L,null,null);
        Car testCar2 = new Car(null,"sample_model1",123467L,null,null);
        CarDto result = testRestTemplate.postForObject(baseUrl, testCar, CarDto.class);
        CarDto result2 = testRestTemplate.postForObject(baseUrl, testCar2, CarDto.class);

        testRestTemplate.delete(baseUrl + "/" + testCar.getId());

        List<CarDto> leftCars = List.of(testRestTemplate.getForObject(baseUrl, CarDto[].class));

        assertEquals(result2.getSerialNumber(),leftCars.get(1).getSerialNumber());
    }

}
