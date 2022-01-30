package com.example.Metadata.controller;

import com.example.Metadata.delegate.MetadataDelegate;
import com.example.Metadata.dto.Metadata;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MetadataController(MetadataDelegate delegate) {
        this.delegate = delegate;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/insert", consumes = "application/x-yaml")
    public ResponseEntity<?> insert(@RequestBody Metadata metadata) {
        try {
            this.delegate.insert(metadata);
            return new ResponseEntity<Metadata>(metadata, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", consumes = "application/yaml")
    public boolean delete() {
        return true;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update", consumes = "application/yaml")
    public boolean update() {
        return true;
    }

    @GetMapping("/findByCompany")
    public Metadata findByCompany(@RequestParam(value = "name") String name) {
        return null;
    }

}
