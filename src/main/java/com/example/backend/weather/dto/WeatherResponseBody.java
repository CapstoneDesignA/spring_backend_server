package com.example.backend.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseBody {

    private String dataType;
    private Items items;
    private Integer pageNo;
    private Integer numOfRows;
    private Integer totalCount;

}

