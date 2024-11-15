package co.kr.bongjae.web.domain.auth.business;

import co.kr.bongjae.web.common.annotation.Business;
import co.kr.bongjae.web.domain.auth.model.LoginRequestDto;
import co.kr.bongjae.web.domain.auth.model.LoginTokenDTO;
import co.kr.bongjae.web.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;

@Business // 비즈니스 로직임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class AuthBusiness {

    /**
     * 인증 서비스
     */
    private final AuthService authService;

    /**
     * 로그인
     * @param authorizationHeader Authorization 헤더
     * @return 로그인 토큰
     */
    public LoginTokenDTO login(String authorizationHeader) {
        LoginRequestDto loginRequestData = authService.getLoginRequestDataFromHeader(authorizationHeader);
        return authService.login(loginRequestData);
    }
}
