package com.jojoldu.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing 을 분리하기 위한 클래스
// @WebMvcTest 는 일반적인 @Configuration 은 스캔하지 않음
@Configuration
@EnableJpaAuditing  // JPA Auditing 활성화
public class JpaConfig {
}
