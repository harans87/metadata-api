package com.example.Metadata.dao;

import com.example.Metadata.dto.Metadata;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class CacheRepositoryTest {

    CacheRepository cacheRepository;

    @BeforeEach
    public void setUp() {
        cacheRepository = new CacheRepository(2);
    }

    @Test
    public void testCacheForLRU() {
        Metadata metadata = new Metadata();
        metadata.company = "fake company";
        metadata.title = "fake title";
        metadata.version = "version";
        this.cacheRepository.set(metadata, metadata.company);
        this.cacheRepository.set(metadata, metadata.title);
        this.cacheRepository.set(metadata, metadata.version);

        this.cacheRepository.print();

        Assert.isTrue(this.cacheRepository.get("version") != null  , "recently accessed element should be present"); 
        Assert.isTrue(this.cacheRepository.get("fake company") == null  , "not accessed element should not be present"); 
    }
    
}
