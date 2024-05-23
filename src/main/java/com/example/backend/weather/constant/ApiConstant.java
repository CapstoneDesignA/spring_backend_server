package com.example.backend.weather.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApiConstant {

    API_URL("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"),
    RN1("RN1"),
    REH("REH");

    private final String origin;

}
