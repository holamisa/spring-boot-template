package co.kr.bongjae.web.domain.auth.service;

import co.kr.bongjae.db.model.user.UserEntity;
import co.kr.bongjae.db.model.user.enums.UserStatus;
import co.kr.bongjae.web.common.error.ErrorCode;
import co.kr.bongjae.web.common.error.TokenErrorCode;
import co.kr.bongjae.web.common.error.UserErrorCode;
import co.kr.bongjae.web.common.exception.ApiException;
import co.kr.bongjae.web.domain.auth.model.LoginRequestDto;
import co.kr.bongjae.web.domain.auth.model.LoginTokenDTO;
import co.kr.bongjae.web.domain.token.service.AuthType;
import co.kr.bongjae.web.domain.token.service.TokenService;
import co.kr.bongjae.web.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Optional;

@Service // 서비스 로직임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class AuthService {

    /**
     * 1. 로그인 - 완료
     * 2. 회원가입
     * 3. 회원 탈퇴
     */

    /**
     * 토큰 서비스
     */
    private final TokenService tokenService;

    /**
     * 패스워드 인코더
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자 서비스
     */
    private final UserService userService;

    /**
     * 헤더에서 로그인 데이터 추출
     * @param authorizationHeader Authorization 헤더
     * @return 로그인 데이터
     */
    public LoginRequestDto getLoginRequestDataFromHeader(String authorizationHeader){
        var encToken = tokenService.getAuthorizationFromHeader(authorizationHeader, AuthType.BASIC);
        byte[] decToken = Base64.getDecoder().decode(encToken);
        String token = new String(decToken);

        var tokenArr = token.split(":");
        if(tokenArr.length != 2){
            throw new ApiException(TokenErrorCode.INVALID_TOKEN);
        }

        return new LoginRequestDto(tokenArr[0], tokenArr[1]);
    }

    /**
     * 로그인
     * @param email 이메일
     * @param password 패스워드
     * @return 로그인 토큰 DTO
     */
    public LoginTokenDTO login(String email, String password) {
        // 1. 이메일로 사용자 조회 + 사용자 없으면 에러
        var userEntity = userService.getUserByEmail(email, true);

        // 2. 패스워드가 일치하지 않으면 에러
//        var encPassword = passwordEncoder.encode(password);
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new ApiException(UserErrorCode.USER_NOT_FOUND);
        }

        // 4. 토큰 발급
        var accessToken = tokenService.issueAccessToken(userEntity.getId());
        var refreshToken = tokenService.issueRefreshToken(userEntity.getId());

        return LoginTokenDTO.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }

    /**
     * 회원가입
     * @param userEntity 사용자 엔티티
     * @return 사용자 엔티티
     */
    public UserEntity register(UserEntity userEntity){
        return Optional.ofNullable(userEntity)
                .map(x -> {
                    // 중복가입 확인
                    var duplicateUser = userService.getUserByEmail(x.getEmail());
                    if(duplicateUser != null){
                        throw new ApiException(UserErrorCode.USER_DUPLICATE);
                    }

                    x.setStatus(UserStatus.REGISTERED);
                    x.setRegisteredAt(LocalDateTime.now());
                    return userService.save(x);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "User Entity NULL"));
    }
}
