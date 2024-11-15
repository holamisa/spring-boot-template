package co.kr.bongjae.web.domain.token.service;

import co.kr.bongjae.web.common.error.ErrorCode;
import co.kr.bongjae.web.common.exception.ApiException;
import co.kr.bongjae.web.domain.token.helper.JwtTokenHelper;
import co.kr.bongjae.web.domain.token.model.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@Service // 서비스 로직임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class TokenService {

    /**
     * JWT 토큰 헬퍼
     */
    private final JwtTokenHelper tokenHelper;

    /**
     * Access Token 발급
     * @param userId 사용자 ID
     * @return 토큰 DTO
     */
    public TokenDTO issueAccessToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);

        return tokenHelper.issueAccessToken(data);
    }

    /**
     * Refresh Token 발급
     * @param userId 사용자 ID
     * @return 토큰 DTO
     */
    public TokenDTO issueRefreshToken(Long userId){
        var data = new HashMap<String, Object>();
        data.put("userId", userId);

        return tokenHelper.issueRefreshToken(data);
    }

    /**
     * 토큰 검증
     * @param token 토큰
     * @return 사용자 ID
     */
    public Long validationToken(String token){
        var map = tokenHelper.validationTokenWithThrow(token);

        var userId = map.get("userId");

        Objects.requireNonNull(userId, () -> {
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        return Long.parseLong(userId.toString());
    }

    /**
     * Authorization 헤더에서 토큰 추출
     * @param authorizationHeader Authorization 헤더
     * @param authType 인증 타입
     * @return 토큰
     */
    public String getAuthorizationFromHeader(String authorizationHeader, AuthType authType){
        return Optional.ofNullable(authorizationHeader)
                .filter(header -> header.split(" ").length == 2)
                .map(header -> {
                    var split = header.split(" ");
                    var expectedPrefix = Optional.ofNullable(authType)
                            .map(type -> switch (type) {
                                case BASIC -> AuthType.BASIC.getType();
                                case BEARER -> AuthType.BEARER.getType();
                            })
                            .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST, "잘못된 Authorization 타입"));

                    if (!split[0].equals(expectedPrefix)) {
                        throw new ApiException(ErrorCode.BAD_REQUEST, "Authorization 타입이 일치하지 않습니다");
                    }

                    return split[1];
                })
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST, "Authorization 헤더가 잘못되었습니다"));
    }
}
