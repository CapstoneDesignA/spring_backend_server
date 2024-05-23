package com.example.backend.store.dto;

import com.example.backend.store.vo.RequestPoint;
import java.net.URL;

public record StoreResponse(
        Long id,
        String name,
        Double latitude, // 위도
        Double longitude,  // 경도
        URL mapUrl  // 네이버 지도 렌더링에 필요한 경로 값,
) {
}
