package com.jojoldu.book.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @SpringBootApplication 으로 인해 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정 됨
// @SpringBootApplication 이 있는 위치부터 설정을 읽어가기 때문에 항상 프로젝트의 최상단에 위치해야 함
// @EnableJpaAuditing : JPA Auditing 어노테이션들을 모두 활성화할 수 있도록 함
// Audit 이란? : Spring Data JPA 에서 시간에 대해서 자동으로 값을 넣어주는 기능
// 도메인을 영속성 컨텍스트에 저장하거나 조회를 수행한 후에 update 를 하는 경우 매번 시간 데이터를 입력하여 주어야 하는데,
// audit 을 이용하면 자동으로 시간을 매핑하여 데이터베이스의 테이블에 넣어주게 됨

// HelloControllerTest 에서 @EnableJpaAuditing 으로 인해 @Entity 클래스가 필요한데 @WebMvcTest 이다 보니 당연히 없음
// @SpringBootApplication 과 @EnableJpaAuditing 을 분리하기 위해 제거
// @EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); // 내장 WAS 실행
    }
}
