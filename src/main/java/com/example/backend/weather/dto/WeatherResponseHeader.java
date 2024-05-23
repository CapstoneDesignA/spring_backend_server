package com.example.backend.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseHeader {

    private String resultCode;
    private String resultMsg;

}
