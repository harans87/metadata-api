package com.example.Metadata.dto;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String title;
    public String version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "")
    @OrderColumn(name = "maintainers")
    @JsonProperty("maintainers")
    public List<Maintainers> maintainers;
    public String company;
    public String website;
    public String source;
    public String license;
    public String description;
}
