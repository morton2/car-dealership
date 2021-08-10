package com.codecool.dealership.service;
import com.codecool.dealership.model.Configuration;
import com.codecool.dealership.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ConfigurationService {

    private ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public Configuration getConfigById(Long id) {
        Optional<Configuration> optionalConfiguration = configurationRepository.findById(id);
        if (optionalConfiguration.isPresent()) return optionalConfiguration.get();
        else throw new RuntimeException("Configuration does not exist");
    }

    public Configuration updateConfigById(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    public Configuration addConfig(Configuration configuration) {
        return configurationRepository.save(configuration);
    }

    public void deleteConfiguration(Long id) {
        configurationRepository.deleteById(id);
    }
}
