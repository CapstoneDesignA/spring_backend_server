package com.example.backend.store.vo;

import com.example.backend.store.dto.StoreResponse;
import java.net.URL;

public record Store(
        Long id,
        String name,
        String code, // AI 서버에 전달하는 코드 값
        Double latitude, // 위도
        Double longitude,  // 경도
        URL mapUrl,  // 네이버 지도 렌더링에 필요한 경로 값,
        RequestPoint point  // 날씨 API 요청 시, 필요한 좌표 값
) {

    public StoreResponse toStoreResponse() {
        return new StoreResponse(id, name, latitude, longitude, mapUrl);
    }

}
