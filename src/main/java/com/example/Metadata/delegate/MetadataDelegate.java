package com.example.Metadata.delegate;

import com.example.Metadata.dao.MetaDataRepository;
import com.example.Metadata.dto.Metadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetadataDelegate {
    private final MetaDataRepository repository;
    
    @Autowired
    public MetadataDelegate(MetaDataRepository repository) {
        this.repository = repository;
    }


    public Metadata save(Metadata metadata) {
        repository.save(metadata);
        return metadata;
    }
}
