package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// web 패키지에 Controller 에 관련된 Class 를 담는다!

// Json 을 반환하는 컨트롤러로 만들어 줌
// Spring framework 에서는 @ResponseBody 를 각 메소드마다 선언했던 것을 Boot 에서는 한번에 사용할 수 있게 해줌
@RestController
public class HelloController {

    // HTTP Method 인 Get 의 요청을 받을 수 있는 API 를 만들어 줌
    // Spring framework 에서는 @RequestMapping("요청 서블릿 주소") 으로 사용되었지만
    // 이 프로젝트는 /hello 로 요청이 오면 문자열 hello 를 반환 하는 기능을 가짐
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        // @RequestParam : 외부에서 API 로 넘긴 파라미터를 가져오는 어노테이션
        // 외부에서 name (@RequestParam("name")) 이란 이름으로 넘긴 파라미터를
        // 메도스 파라미터 name(string name)에 저장하게 됨
        // 이 부분도 Spring framework 에서 진행햇던 것과 동일하다.
        return new HelloResponseDto(name, amount);
    }
}
