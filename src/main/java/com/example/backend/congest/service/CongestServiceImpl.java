package com.example.backend.congest.service;

import com.example.backend.congest.dto.CongestAIResponse;
import com.example.backend.congest.dto.CongestResponse;
import com.example.backend.store.repository.StoreRepository;
import com.example.backend.store.vo.Store;
import com.example.backend.weather.constant.ApiConstant;
import com.example.backend.weather.dto.WeatherItem;
import com.example.backend.weather.service.WeatherClientService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CongestServiceImpl implements CongestService {

    private final WeatherClientService weatherservice;
    private final StoreRepository storeRepository;
    private final WebClient.Builder webClientBuilder;

    public CongestServiceImpl(final WeatherClientService weatherservice,
                              final StoreRepository storeRepository,
                              final Builder webClientBuilder) {
        this.weatherservice = weatherservice;
        this.storeRepository = storeRepository;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public CongestResponse getCongest(Long id) {
        final Optional<Store> findStore = storeRepository.findStoreById(id);
        if (findStore.isEmpty()) {
            throw new IllegalArgumentException("해당하는 가게가 없습니다.");
        }
        final Store store = findStore.get();
        final LocalDate today = LocalDate.now();
        final LocalTime now = LocalTime.now();

        log.info("store={}", store);

        final WeatherItem filteredValue = weatherservice.getFilteredValue("1", "2000",
                today.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                now.format(DateTimeFormatter.ofPattern("HHmm")),
                String.valueOf(store.point().requestX()),
                String.valueOf(store.point().requestY()),
                ApiConstant.RN1);

        final String obsrValue = filteredValue.getObsrValue(); // 강수량
        log.info("강수량={}", obsrValue);
        // AI 모델에게 전달
        final CongestAIResponse response = getCongestValueFromAI(obsrValue).block();
        if (!response.isSuccess()) {
            throw new IllegalArgumentException("AI에 강수량 다시 보내기");
        }
        final String congestion = response.data().congestion();
        return new CongestResponse(
                id,
                store.latitude(),
                store.longitude(),
                Double.valueOf(congestion));
    }

    private Mono<CongestAIResponse> getCongestValueFromAI(final String obsrValue) {
        final WebClient webClient = webClientBuilder
                .baseUrl("http://localhost:5000/test/model").build();
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.queryParam("rain_percent", obsrValue).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(CongestAIResponse.class);
    }

}
