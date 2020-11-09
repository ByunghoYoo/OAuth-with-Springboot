package com.jojoldu.book.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// 사용자의 권한을 관리할 Enum 클래스
// Enum 클래스란?
// 클래스처럼 보이게 하는 상수
// 서로 관련있는 상수들끼리 모아 상수들을 정의하는것
// Enum 클래스 형을 기반으로 한 클래스형 선언
// Enum 클래스 특징
// 1. 열거형으로 선언된 순서에 따라 0부터 index 값을 가진다.(순차적으로 증가)
// 2. enum 열거형으로 지정된 상수들은 모두 대문자로 선언한다.
// 3. 열거형 변수들을 선언한 후 마지막에 세미콜론(;)을 찍지 않는다.
// 4. 상수와 특정 값을 연결시킬경우 마지막에 세미콜론(;)을 붙여줘야한다.

@Getter
@RequiredArgsConstructor
public enum Role {

    // 스프링 시큐리티에서는 권한 코드에 항상 "ROLE_" 이 앞에 있어야만 함
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
