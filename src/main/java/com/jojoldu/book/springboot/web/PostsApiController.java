package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

// 스프링에서 Bean 을 주입받는 방식으로 @Autowired, setter, 생성자 가 있다.
// 이 중 가장 권장하는 방식이 생성자로 주입받는 방식 (@Autowired 는 권장하지 않음)
// @RequiredArgsConstructor 를 통해 생성자 생성, final 이 선언된 모든 필드를 인자값으로 하는 생성자를 대신 생성
@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    // @RequestBody
    // Get 방식은 Http Header 에 담아 옴 (이 경우 보내는 데이터의 양이 한정됨)
    // Post 방식은 Http Body 에 담아 옴 (이 경우 보내는 데이터의 양이 한정되지 않기 때문에 대용량의 데이터를 받아올 때 사용함)
    // 클라이언트가 전송하는 Http 요청의 Body 내용을 Java Object 로 변환시켜주는 역할 (따라서 반드시 Post 요청과 함께 사용)
    // POST 방식으로 Json 의 형태로 넘겨온 데이터를 객체로 바인딩하기 위해 사용
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    // @PathVariable
    // 좀 더 깔끔한 URI 를 만들 수 있다. (get 방식 호출일 경우, 쿼리스트링으로 URI 가 표시되기 때문)
    // 매핑하는 path 에 {}를 활용해 변수처럼 적어준 뒤
    // @PathVariable 어노테이션 뒤에 {} 안에 적은 변수명을 name = "속성값" 으로 넣음(변수를 달리 활용 시)
    // 그 후 이를 받을 자료형과 변수명을 선언해 준다.
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
