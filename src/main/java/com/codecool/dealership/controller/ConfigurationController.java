package com.codecool.dealership.controller;
import com.codecool.dealership.dto.ConfigurationDto;
import com.codecool.dealership.model.Configuration;
import com.codecool.dealership.service.ConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/configuration")
public class ConfigurationController {

    private ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getConfigById(@PathVariable("id") Long id) {
            ConfigurationDto configurationDto = configurationService.getConfigById(id);
            if (configurationDto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(configurationDto);
        }

    @GetMapping
    public ResponseEntity<?> getConfigurations() {
        List<ConfigurationDto> configurationDto = configurationService.retrieveConfigurations();
        return ResponseEntity.ok(configurationDto);
    }


    @PutMapping
    public ResponseEntity<?> updateConfigById(@RequestBody @Valid Configuration configuration, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(configurationService.addAndUpdateConfig(configuration));
    }

    @PostMapping
    public ResponseEntity<?> createConfig(@RequestBody @Valid Configuration configuration, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(configurationService.addAndUpdateConfig(configuration));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConfiguration(@PathVariable("id") Long id) {
        if(configurationService.deleteConfiguration(id).equals("Configuration successfully deleted")) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}