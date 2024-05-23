package com.example.backend.congest.dto;

public record CongestAIResponse(
        boolean isSuccess,
        String message,
        CongestData data
) {
}
