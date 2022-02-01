package com.example.Metadata.dao;

import java.util.List;

import com.example.Metadata.dto.Metadata;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MetaDataRepository extends CrudRepository<Metadata, Long> {

    Metadata findByCompany(final String company);
    Metadata findByTitle(final String title);
    Metadata findByTitleAndVersion(final String title, final String version);
    List<Metadata> findByDescriptionContainingIgnoreCase(final String description);
    @Query("SELECT m FROM Metadata m WHERE m.description LIKE %:description%")
    List<Metadata> searchByDescriptionLike(@Param("description") String description);
}
