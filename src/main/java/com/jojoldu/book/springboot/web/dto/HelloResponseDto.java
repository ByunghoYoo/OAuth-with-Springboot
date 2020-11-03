package com.jojoldu.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 이전 Spring framework 에서 진행해 보았고 간단함
@Getter // get 메소드 생성
@RequiredArgsConstructor    // 선언된 모든 final 필드가 포함된 생성자 생성
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
