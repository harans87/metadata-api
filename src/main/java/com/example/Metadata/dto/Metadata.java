package com.example.Metadata.dto;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Metadata {
    @Id
    @SequenceGenerator(
        name = "metadata_sequence",
        sequenceName = "metadata_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "metadata_sequence")
    public Long id;
    public String title;
    public String version;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Maintainers.class)
    @JoinColumn(name = "maintainers_fk", referencedColumnName = "id")
    @JsonProperty("maintainers")
    public List<Maintainers> maintainers;
    public String company;
    public String website;
    public String source;
    public String license;
    public String description;
}
