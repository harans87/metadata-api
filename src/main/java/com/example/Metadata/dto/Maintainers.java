package com.example.Metadata.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Maintainers {
    @Id
    @JsonProperty("name")
    public String name;
    @JsonProperty("email")
    public String email;
    
}
