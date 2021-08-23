package com.codecool.dealership.integration.test;

import com.codecool.dealership.model.Configuration;
import com.codecool.dealership.model.test.ConfigurationDto;
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
public class ConfigurationTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUrl() {
        this.baseUrl = "http://localhost:" + port + "/configuration";
    }

    @Test
    public void addConfiguration_returnSameConfiguration() {
        Configuration testConfiguration = new Configuration(null,1L,1L,1L,1L,1L,null);
        ConfigurationDto result = testRestTemplate.postForObject(baseUrl, testConfiguration, ConfigurationDto.class);
        assertEquals(testConfiguration.getTrim(), result.getTrim());
    }

    @Test
    public void getConfiguration_returnEmptyList() {
        List<ConfigurationDto> configurationList = List.of(testRestTemplate.getForObject(baseUrl, ConfigurationDto[].class));
        assertEquals(0, configurationList.size());
    }

    @Test
    public void getConfigurationById_returnConfigurationWithTheSameId() {
        Configuration testConfiguration = new Configuration(null,1L,1L,1L,1L,1L,null);
        ConfigurationDto testConfigurationResult = testRestTemplate.postForObject(baseUrl, testConfiguration, ConfigurationDto.class);
        ConfigurationDto result = testRestTemplate.getForObject(baseUrl + "/" + testConfigurationResult.getId(), ConfigurationDto.class);
        assertEquals(testConfigurationResult.getId(), result.getId());
    }

    @Test
    public void updateConfiguration_returnWithUpdatedModel() {
        Configuration testConfiguration = new Configuration(null,1L,1L,1L,1L,1L,null);
        ConfigurationDto result = testRestTemplate.postForObject(baseUrl, testConfiguration, ConfigurationDto.class);
        Configuration testConfigurationModified = new Configuration(result.getId(),2L,2L,1L,1L,1L,null);
        testRestTemplate.put(baseUrl, testConfigurationModified);
        ConfigurationDto resultModified = testRestTemplate.getForObject(baseUrl+"/"+ result.getId(),ConfigurationDto.class);
        assertEquals(2, resultModified.getTrim());
    }

    @Test
    public void deleteConfiguration_returnWithLeftConfigurations() {
        Configuration testConfiguration = new Configuration(null,1L,1L,1L,1L,1L,null);
        Configuration testConfiguration2 = new Configuration(null,2L,2L,1L,1L,1L,null);
        ConfigurationDto result = testRestTemplate.postForObject(baseUrl, testConfiguration, ConfigurationDto.class);
        ConfigurationDto result2 = testRestTemplate.postForObject(baseUrl, testConfiguration2, ConfigurationDto.class);

        testRestTemplate.delete(baseUrl + "/" + testConfiguration.getId());

        List<ConfigurationDto> leftConfigurations = List.of(testRestTemplate.getForObject(baseUrl, ConfigurationDto[].class));

        assertEquals(result2.getTrim(),leftConfigurations.get(1).getTrim());
    }

}
