package com.example.Metadata.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Maintainers {
    @Id
    private String email;
    private String name;
}
