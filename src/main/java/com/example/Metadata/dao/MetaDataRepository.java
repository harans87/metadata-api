package com.example.Metadata.dao;

import com.example.Metadata.dto.Metadata;
import org.springframework.data.repository.CrudRepository;

public interface MetaDataRepository extends CrudRepository<Metadata, Long> {

    Metadata findByCompany(final String company);
    Metadata findByTitle(final String title);
}
