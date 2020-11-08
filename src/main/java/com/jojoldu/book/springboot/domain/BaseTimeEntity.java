package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// BaseTimeEntity 클래스는 모든 Entity 의 상위 클래스가 되어
// Entity 들의 createdDate, modifiedDate 를 자동으로 관리하는 역할

// @MappedSuperclass : JPA Entity 클래스들이 BaseTimeEntity 을 상속할 경우 필드들도 칼럼으로 인식하도록 함
// @EntityListeners(AuditingEntityListener.class) : BaseTimeEntity 클래스에 Auditing 기능을 포함시킴
// JPA Auditing : 생성일, 수정일과 같은 시간에 대해서 자동으로 값을 넣어주는 기능
// 도메인을 영속성 컨텍스트에 저장하거나 조회를 수행한 후에 update 를 하는 경우 매번 시간 데이터를 입력하여 주어야 하는데,
// audit 을 이용하면 자동으로 시간을 매핑하여 데이터베이스의 테이블에 넣어주게 됨
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    // Entity 가 생성되어 저장될 때 시간이 자동 저장됨
    @CreatedDate
    private LocalDateTime createdDate;

    // 조회한 Entity 의 값을 변경할 때 시간이 자동 저장됨
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
