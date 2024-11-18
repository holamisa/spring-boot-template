package co.kr.bongjae.web.domain.auth.business;

import co.kr.bongjae.db.model.user.UserEntity;
import co.kr.bongjae.web.common.annotation.Business;
import co.kr.bongjae.web.domain.auth.model.LoginRequestDto;
import co.kr.bongjae.web.domain.auth.model.LoginTokenDTO;
import co.kr.bongjae.web.domain.auth.model.UserRegisterRequestDTO;
import co.kr.bongjae.web.domain.auth.service.AuthService;
import co.kr.bongjae.web.domain.user.business.UserConverter;
import lombok.RequiredArgsConstructor;

@Business // 비즈니스 로직임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class AuthBusiness {

    /**
     * 인증 서비스
     */
    private final AuthService authService;

    /**
     * 인증 컨버터
     */
    private final AuthConverter authConverter;

    /**
     * 사용자 컨버터
     */
    private final UserConverter userConverter;

    /**
     * 로그인
     * @param authorizationHeader Authorization 헤더
     * @return 로그인 토큰
     */
    public LoginTokenDTO login(String authorizationHeader) {
        LoginRequestDto loginRequestData = authService.getLoginRequestDataFromHeader(authorizationHeader);
        return authService.login(loginRequestData.email(), loginRequestData.password());
    }

    /**
     * 회원가입
     * @param request 회원가입 요청
     * @return 로그인 토큰
     */
    public LoginTokenDTO register(UserRegisterRequestDTO request) {

        UserEntity newUser = authConverter.toEntity(request);
        UserEntity userEntity = authService.register(newUser);

        // 1. 생성된 신규 사용자 데이터 리턴
        // return userConverter.toDto(entity);

        // 2. 신규 사용자 생성 후 로그인 처리
        return authService.login(userEntity);
    }
}
