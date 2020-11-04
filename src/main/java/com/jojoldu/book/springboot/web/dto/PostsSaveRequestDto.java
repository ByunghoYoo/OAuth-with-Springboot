package com.jojoldu.book.springboot.web.dto;

import com.jojoldu.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Entity 클래스(Posts)와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성한 이유 (Entity 클래스를 Request/Response 클래스로 사용해선 안됨!)
// Entity 클래스는 Database 와 맞닿은 핵심 클래스인데 사소한 기능 변경을 위해 테이블과 연결된 Entity 클래스를 변경하는 것은 큰 변경임
// 반면 Request/Response 용 Dto 는 View 를 위한 클래스라 자주 변경이 필요함
// 따라서 View Layer 와 DB Layer 의 역할 분리를 철저하게 해주는 것이 좋음
// ex) Controller 에서 결괏값으로 여러 테이블을 조인해서 줘야할 경우가 빈번하므로 Entity 클래스만으로 표현하기 어려운 경우가 많음
// 꼭 Entity 클래스와 Controller 에서 쓸 Dto 는 분리해서 사용
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
