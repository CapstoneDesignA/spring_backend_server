package com.example.backend.congest.dto;

public record CongestResponse(
        Long id,
        Double latitude, // 위도
        Double longitude,  // 경도
        Double congest
) {
}
