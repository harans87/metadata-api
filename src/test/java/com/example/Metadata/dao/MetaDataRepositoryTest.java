package com.example.Metadata.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.Metadata.dto.Metadata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MetaDataRepositoryTest {

    @Autowired
    private MetaDataRepository metaDataRepository;

    @Test
    @Sql("createMetadata.sql")
    public void testDeleteByTitle() {
        long deletedRecords = metaDataRepository.deleteByTitle("fake title");
        assertTrue(() -> deletedRecords == 1);
    }

    @Test
    @Sql("createMetadata.sql")
    public void testfindByCompany() {
        Metadata matchedRecord = metaDataRepository.findByCompany("fake company");
        assertTrue(() -> matchedRecord.company.equalsIgnoreCase("fake company"));
    }

}
