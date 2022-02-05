package com.example.Metadata.delegate;

import java.util.List;

import com.example.Metadata.dao.CacheRepository;
import com.example.Metadata.dao.MetaDataRepository;
import com.example.Metadata.dto.Metadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MetadataDelegate {
    private final MetaDataRepository repository;
    private final CacheRepository cacheRepository;
    
    @Autowired
    public MetadataDelegate(MetaDataRepository repository, CacheRepository cacheRepository) {
        this.repository = repository;
        this.cacheRepository = cacheRepository;
    }


    public Metadata save(Metadata metadata) {
        Metadata saveMetadata = repository.save(metadata);
        // write through cache
        cacheRepository.set(metadata, saveMetadata.company);
        cacheRepository.set(metadata, saveMetadata.title);
        return saveMetadata;
    }
    
    public Metadata search(String searchTerms) {
        return null;
    }

    public Metadata findByCompany(String companyName) {
        // check in cache first, if not get it from DB.
        return null != cacheRepository.get(companyName) ? cacheRepository.get(companyName).getValue() : repository.findByCompany(companyName);

    }

    public Metadata findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public Metadata findByTitleAndVersion(String title, String version) {
        return repository.findByTitleAndVersion(title, version);
    }

    public List<Metadata> findByDescription(String description) {
        return repository.findByDescriptionContainingIgnoreCase(description);
    }

    public void update(Metadata metadata) {
        repository.save(metadata);
    }

    @Transactional
    public void delete(String title) {
        repository.deleteByTitle(title);
    }
}
