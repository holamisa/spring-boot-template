package co.kr.bongjae.web.domain.token.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Getter
public enum AuthType {

    BASIC("Basic"),

    BEARER("Bearer");

    private final String type;
}
