package com.example.backend.weather.presentation;

import com.example.backend.weather.constant.ApiConstant;
import com.example.backend.weather.dto.WeatherItem;
import com.example.backend.weather.service.WeatherClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherClientService weatherClientService;

    // 강수량 가져오는 API
    @GetMapping("/api/getObsr")
    public WeatherItem getObsrValue(@RequestParam String baseDate, String baseTime) {
        return weatherClientService.getFilteredValue("1","2000",baseDate,baseTime,"80","127", ApiConstant.RN1);
    }




}
