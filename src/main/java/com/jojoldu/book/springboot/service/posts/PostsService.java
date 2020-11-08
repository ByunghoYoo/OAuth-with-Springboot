package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // 선언적 트랜잭션
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // update 기능에 쿼리를 날리는 부분이 없는데 이게 가능한 이유는 JPA 의 영속성 컨텍스트 때문
    // 영속성 컨텍스트란, 엔티티를 영구 저장하는 환경
    // JPA 의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈림
    // JPA 의 엔티티 매니저가 활성화된 상태로 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태
    // 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영
    // 즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다! (더티 체킹)
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    // readOnly = true : 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선 됨
    // 등록, 삭제, 수정 기능이 전혀 없는 서비스 메소드에서만 사용하는 것을 추천
    // postsRepository 결과로 넘어온 Posts 의 Stream 을 map 을 통해 PostsListResponseDto 변환 -> List 로 반환하는 메소드
    // Stream : 람다를 활용할 수 있는 기술 중 하나.
    // 자바 8 이전에는 배열 또는 컬렉션 인스턴스를 다루는 방법은 for 또는 foreach 문을 돌면서 하나씩 꺼내서 다루는 방법
    // 스트림은 '데이터의 흐름'. 배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공된 결과를 얻음
    // 또한 람다를 이용해서 코드의 양을 줄이고 간결하게 표현이 가능. 즉 배열과 컬렉션을 함수형으로 처리할 수 있음.
    // 또 하나의 장점은 병렬 처리가 가능함 (https://futurecreator.github.io/2018/08/26/java-8-streams/)
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
        // .map(PostsListResponseDto::new) = .map(posts -> new PostsListResponseDto(posts))
        // .map(람다식) : 요소들을 특정조건에 해당하는 값으로 변환
        // ex) list.stream().map(s -> s.toUpperCase()); : 리스트의 요소들을 대문자로 변경
        // .collect(Collectors.toList()) : 스트림에서 작업한 결과를 담은 리스트로 반환
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // JpaRepository 에서 이미 delete 메소드를 지원함
        // 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id 로 삭제할 수 있음
        // 존재하는 Posts 인지 확인을 위해 엔티티 조회 후 그대로 삭제
        postsRepository.delete(posts);
    }

}
