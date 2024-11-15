package co.kr.bongjae.web.domain.auth.controller;

import co.kr.bongjae.web.domain.auth.business.AuthBusiness;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Rest 컨트롤러임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
@RequestMapping("/api/auth") // API 주소 매핑
@Tag(name = "AuthApi", description = "인증 API Document") // Swagger 문서화
public class AuthApiController {

    /**
     * 인증 비즈니스
     */
    private final AuthBusiness authBusiness;
}
