package com.example.Metadata.controller;

import com.example.Metadata.dto.Metadata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetaDataController {

    @RequestMapping(method=RequestMethod.PUT, value="/insert", consumes="application/x-yaml")
    public boolean insert(@RequestBody Metadata metadata) {
        return true;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/delete", consumes="application/yaml")
    public boolean delete() {
        return true;
    }

    @RequestMapping(method=RequestMethod.POST, value="/update", consumes="application/yaml")
    public boolean update() {
        return true;
    }

    @GetMapping("/findByCompany")
    public Metadata findByCompany(@RequestParam(value ="name") String name) {
        return null;
    }
    
}
