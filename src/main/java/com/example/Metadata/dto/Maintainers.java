package com.example.Metadata.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Maintainers {
    @Id
    @SequenceGenerator(
        name = "maintainers_sequence",
        sequenceName = "maintainers_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "maintainers_sequence")
    public Long id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("email")
    public String email;
    
}
