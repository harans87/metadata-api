package com.example.Metadata.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    private final String errorClass;
    private final String errorDescription;
}
