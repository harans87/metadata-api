package com.example.Metadata.utils;

import com.example.Metadata.dto.RequestValidationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class SchemaValidator {

    private final ObjectMapper objectMapper;

    @Autowired
    public SchemaValidator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void validate(JsonSchema schema,Object request) throws JsonProcessingException {
        Set<ValidationMessage> errors = schema.validate(requestToJsonNode(request));
        processValidationMessages(errors);
    }

    JsonNode requestToJsonNode(Object request) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(request);
        return objectMapper.readTree(json);
    }

    void processValidationMessages(Set<ValidationMessage> validationMessages) {
        Optional.ofNullable(validationMessages)
                .filter(s -> !s.isEmpty())
                .map(set -> set.stream()
                .filter(o -> o != null && StringUtils.isNotBlank(o.getMessage()))
                .map(ValidationMessage::getMessage)
                .collect(Collectors.joining("\n")))
                .filter(StringUtils::isNotBlank)
                .ifPresent(messages -> {
                    throw new RequestValidationException("Schema validation errors\n" + messages);
                });
    }

}
