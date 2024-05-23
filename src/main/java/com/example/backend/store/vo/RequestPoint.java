package com.example.backend.store.vo;

public record RequestPoint(
        Integer requestX, // 날씨 요청에 사용되는 x 값
        Integer requestY  // 날씨 요청에 사용되는 y 값
) {
}
