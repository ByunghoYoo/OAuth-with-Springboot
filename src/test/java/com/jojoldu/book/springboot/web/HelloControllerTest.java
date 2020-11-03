package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// 테스트를 진행할 때 JUnit 에 내장된 실행자 외에 다른 실행자를 실행시킴
// 여기선 SpringRunner 라는 스프링 실행자를 사용함
// 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함
@RunWith(SpringRunner.class)
// 여러 스프링 테스트 어노테이션 중, Web(Spring MVC) 에 집중할 수 있는 어노테이션
// 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음
// 단, @Service, @Component, @Repository 등은 사용할 수 없음
// 여기선 컨트롤러만 사용하기 때문에 선언
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    // 스프링이 관리하는 빈(Bean)을 주입 받음 (Spring framework 랑 동일한 듯)
    @Autowired
    private MockMvc mvc;
    // MockMvc
    // 웹 API 를 테스트할 때 사용함
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        // MockMvc 를 통해 /hello 주소로 HTTP GET 요청을 함
        // 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있음
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
        // andExpect(status().isOk()) : mvc.perform 의 결과를 검증
        // HTTP Header 의 Status 를 검증
        // 우리가 흔히 알고 있는 200, 404, 500 등의 상태 검증
        // 여기선 OK 즉, 200 인지 아닌지를 검증

        // andExpect(content().string(hello)) : mvc.perform 의 결과를 검증
        // 응답 본문의 내용을 검증
        // Controller 에서 "hello" 를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                    get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

        // param : API 테스트할 때 사용될 요청 파라미터를 설정
        // 단, 값은 String 만 허용
        // 그래서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능
        // jsonPath : JSON 응답값을 필드별로 검증할 수 있는 메소드
        // $를 기준으로 필드명을 명시
        // 여기서는 name 과 amount 를 검증하니 $.name, $.amount 로 검증
    }
}
