package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 같은 코드를 방복되는 것을 방지하기 위해 어노테이션 기반으로 개선
// SessionUser user = (SessionUser)httpSession.getAttribute("user");
// 이 부분을 메소드 인자로 세션값을 바로 받을 수 있도록 세팅해보자.

// @Target(ElementType.PARAMETER) : 이 어노테이션이 생성될 수 있는 위치를 지정
//                                  PARAMETER 로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있음
//                                  이 외에도 클래스 선언문에 쓸 수 있는 TYPE 등이 있음
// @interface : 이 파일을 어노테이션 클래스로 지정
//              LoginUser 라는 이름을 가진 어노테이션이 생성된 것임
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
