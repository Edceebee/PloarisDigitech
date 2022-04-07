package com.example.PolarisAssessment.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    /**
     * Configuration for model mapper
     * @return model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
