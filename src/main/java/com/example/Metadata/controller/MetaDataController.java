package com.example.Metadata.controller;

import com.example.Metadata.delegate.MetadataDelegate;
import com.example.Metadata.dto.ErrorResponse;
import com.example.Metadata.dto.Metadata;
import com.example.Metadata.dto.RequestValidationException;
import com.example.Metadata.utils.SchemaValidator;
import com.networknt.schema.JsonSchema;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetadataController {
    private final MetadataDelegate delegate;
    private final SchemaValidator schemaValidator;
    @Qualifier("jsonSchemaForValidation")
    private final JsonSchema validationSchema;

    @Autowired
    public MetadataController(MetadataDelegate delegate, SchemaValidator schemaValidator, JsonSchema validationSchema) {
        this.delegate = delegate;
        this.schemaValidator = schemaValidator;
        this.validationSchema = validationSchema;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/save", consumes = "application/x-yaml")
    public ResponseEntity<?> save(@RequestBody Metadata metadata) {
        try {
            this.schemaValidator.validate(validationSchema, metadata);
            this.delegate.save(metadata);
            return new ResponseEntity<Metadata>(metadata, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ErrorResponse>(constructErrorResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity<?> delete(@RequestParam(value = "title") String title) {
        try {
            this.delegate.delete(title);
            return new ResponseEntity<String>(title, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ErrorResponse>(constructErrorResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update", consumes = "application/x-yaml")
    public ResponseEntity<?> update(@RequestBody Metadata metadata) {
        try {
            this.delegate.update(metadata);
            return new ResponseEntity<Metadata>(metadata, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<ErrorResponse>(constructErrorResponse(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/findByCompany")
    public Metadata findByCompany(@RequestParam(value = "name") String name) {
        return this.delegate.findByCompany(name);
    }

    @GetMapping("/findByTitle")
    public Metadata findByTitle(@RequestParam(value = "title") String title) {
        return this.delegate.findByTitle(title);
    }

    @GetMapping("/findByTitleAndVersion")
    public Metadata findByTitle(@RequestParam(value = "title") String title,
            @RequestParam(value = "version") String version) {
        return this.delegate.findByTitleAndVersion(title, version);
    }

    @GetMapping("/findByDescription")
    public List<Metadata> findByDescription(@RequestParam(value = "description") String description) {
        return this.delegate.findByDescription(description);
    }

    private ErrorResponse constructErrorResponse(Exception ex) {
        return ErrorResponse.builder()
                .errorClass(ex.getClass().toString())
                .errorDescription(ex.getMessage())
                .build();
    }

}
