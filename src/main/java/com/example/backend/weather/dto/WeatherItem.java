package com.example.backend.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherItem {

    private String baseDate;
    private String baseTime;
    private String category;
    private int nx;
    private int ny;
    private String obsrValue;

}
