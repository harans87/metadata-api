package com.example.Metadata.Configuration;

import com.github.fge.jsonschema.main.JsonSchemaFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonSchemaConfiguration {
    
    @Bean("jsonSchemaValidator")
    public void jsonSchemaValidator() {
    }
}
