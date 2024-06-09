package com.example.backend.weather.service;

import com.example.backend.weather.constant.ApiConstant;
import com.example.backend.weather.dto.WeatherItem;
import com.example.backend.weather.dto.WeatherJSONResponse;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class WeatherClientService {

    @Value("${api.key}")
    private String apiKey;
    private final WebClient.Builder webClientBuilder;

    public WeatherClientService(final WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public WeatherItem getFilteredValue(String pageNo,
                                        String numOfRows,
                                        String baseDate,
                                        String baseTime,
                                        String nx,
                                        String ny,
                                        ApiConstant category) {
        log.info("pageNo ={}", pageNo);
        log.info("numOfRows ={}", numOfRows);
        log.info("nx ={}", nx);
        log.info("ny ={}", ny);
        log.info("baseDate ={}", baseDate);
        log.info("baseTime ={}", baseTime);
        Mono<WeatherJSONResponse> weatherResponse = callWeatherApi(pageNo, numOfRows, baseDate,
                baseTime, nx, ny);

        final WeatherJSONResponse json = weatherResponse.block();
        if (json.getResponse().getBody() == null) {
            return new WeatherItem(baseDate, baseTime, category.getOrigin(),
                    Integer.parseInt(nx), Integer.parseInt(ny), "0");
        }

        return filterWeatherValue(json, category);
    }

    private Mono<WeatherJSONResponse> callWeatherApi(
            String pageNo,
            String numOfRows,
            String baseDate,
            String baseTime,
            String nx,
            String ny) {
        final WebClient webClient = webClientBuilder
                .baseUrl(ApiConstant.API_URL.getOrigin())
                .build();

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("ServiceKey", apiKey)
                        .queryParam("pageNo", pageNo)
                        .queryParam("numOfRows", numOfRows)
                        .queryParam("base_date", baseDate)
                        .queryParam("base_time", baseTime)
                        .queryParam("nx", nx)
                        .queryParam("ny", ny)
                        .queryParam("dataType", "JSON")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WeatherJSONResponse.class);
    }

    private WeatherItem filterWeatherValue(WeatherJSONResponse json,
                                           ApiConstant category) {
        if (json == null) {
            throw new IllegalArgumentException("날씨 JSON 응답 값이 없습니다.");
        }
        return json.getResponse().getBody().getItems().getItem().stream()
                .filter(weatherItem -> weatherItem.getCategory().equals(category.getOrigin()))
                .findFirst()
                .orElseThrow();
    }

}


