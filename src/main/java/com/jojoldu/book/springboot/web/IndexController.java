package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.config.auth.LoginUser;
import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import com.jojoldu.book.springboot.service.posts.PostsService;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

// 페이지에 관련된 컨트롤러는 IndexController 사용
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정
    // 앞의 경로는 src/main/resource/templates 로, 뒤의 확장자는 .mustache 가 붙음
    // 즉, src/main/resource/templates/index.mustache 로 전환되어 View Resolver 가 처리함

    // !어노테이션 사용으로 주석 처리 - public String index(Model model) {
    // @LoginUser SessionUser user : 기존 (SessionUser)httpSession.getAttribute("user") 로 가져오던 세션 정보 값 개선
    // 어느 컨트롤러든지 @LoginUser 만 사용하면 세션 정보를 가져올 수 있게 됨
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // index.mustache 에서 userName 을 사용할 수 있게 userName 을 model 에 저장하는 코드 작성
        // 앞서 작성된 CustomOAuth2UserService 에서 로그인 성공 시 SessionUser 를 저장하도록 구성
        // 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있음
        // !어노테이션 사용으로 주석 처리 - SessionUser user = (SessionUser)httpSession.getAttribute("user");

        // 세션에 저장된 값이 있을 때만 model 에 userName 으로 등록
        // 세션에 저장된 값이 없으면 model 엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 됨
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
