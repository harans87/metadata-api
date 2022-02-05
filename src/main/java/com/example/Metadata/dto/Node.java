package com.example.Metadata.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node<T> {
    String key;
    T value;
    
}
