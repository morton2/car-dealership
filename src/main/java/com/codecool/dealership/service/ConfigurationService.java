package com.codecool.dealership.service;
import com.codecool.dealership.dto.ConfigurationDto;
import com.codecool.dealership.model.Configuration;
import com.codecool.dealership.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConfigurationService {

    private ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public ConfigurationDto getConfigById(Long id) {
        Configuration configuration = configurationRepository.findById(id).orElse(null);
        if (configuration == null) {
            return null;
        }
        return new ConfigurationDto(configuration);
    }

    public List<ConfigurationDto> retrieveConfigurations() {
        return configurationRepository.findAll().stream().map(ConfigurationDto::new).collect(Collectors.toList());
    }

    public ConfigurationDto addAndUpdateConfig(Configuration configuration) {
        return new ConfigurationDto(configurationRepository.save(configuration));    }

    public String deleteConfiguration(Long id) {
        if (configurationRepository.existsById(id)) {
            configurationRepository.deleteById(id);
            return "Configuration successfully deleted";
        } else {
            return "Configuration not found";
        }
    }

}
