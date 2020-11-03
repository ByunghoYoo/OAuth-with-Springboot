package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// 보통 MyBatis 에서 DAO 라고 불리는 DB Layer 접근자
// JPA 에선 Repository 라고 부르며 인터페이스로 생성
// 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입> 을 상속하면 기본적인 CRUD 메소드가 자동으로 생성
// @Repository 를 추가할 필요도 없음. 단, Entity 클래스와 기본 Entity Repository 는 함께 위치해야 함
// Entity 클래스는 Repository 없이는 제대로 역할을 할 수 없음
// 그러므로 도메인별로 프로젝트를 분리해야 한다면 둘이 함께 움직여야 하므로 도메인 패키지에서 함께 관리
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
