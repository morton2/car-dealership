package com.codecool.dealership.controller;
import com.codecool.dealership.model.Configuration;
import com.codecool.dealership.service.ConfigurationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

    private ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping("/{id}")
    public Configuration getConfigById(@PathVariable("id") Long id) {
        return configurationService.getConfigById(id);
    }

    @PutMapping
    public Configuration updateConfigById(@RequestBody Configuration configuration) {
        return configurationService.updateConfigById(configuration);
    }

    @PostMapping
    public Configuration addConfig(@RequestBody Configuration configuration) {
        return configurationService.addConfig(configuration);
    }

    @DeleteMapping("/{id}")
    public void deleteConfiguration(@RequestParam("id") Long id) {
        configurationService.deleteConfiguration(id);
    }
}