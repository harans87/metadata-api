package com.example.Metadata.Configuration;

import java.io.IOException;

import com.example.Metadata.utils.FileLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.SpecVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonSchemaConfiguration {
    
    @Bean("jsonSchemaForValidation")
    public JsonSchema jsonSchemaLoader(ObjectMapper objectMapper) {
        try {
            return getJsonSchema(objectMapper, FileLoader.readFile("jsonSchema.json"));
        } catch (IOException e) {
            return null;
        }
    }

    private JsonSchema getJsonSchema(ObjectMapper objectMapper, String schema) throws JsonMappingException, JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(schema);
        return JsonSchemaFactory.builder(JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7))
            .objectMapper(objectMapper)
            .build()
            .getSchema(jsonNode);
    }
}
