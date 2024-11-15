package co.kr.bongjae.web.domain.token.helper;

import co.kr.bongjae.web.common.error.TokenErrorCode;
import co.kr.bongjae.web.common.exception.ApiException;
import co.kr.bongjae.web.domain.token.model.TokenDTO;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 토큰 도우미
 */
@Component
public class JwtTokenHelper {

    // application.yaml 파일에 설정 값 읽기
    @Value("${token.secret.key}")
    private String secretKey;
    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;
    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    /**
     * Access Token 발급
     * @param data 토큰에 담을 데이터
     * @return 토큰 DTO
     */
    public TokenDTO issueAccessToken(Map<String, Object> data) {
        return createJwtToken(data, accessTokenPlusHour);
    }

    /**
     * Refresh Token 발급
     * @param data 토큰에 담을 데이터
     * @return 토큰 DTO
     */
    public TokenDTO issueRefreshToken(Map<String, Object> data) {
        return createJwtToken(data, refreshTokenPlusHour);
    }

    /**
     * JWT 토큰 생성
     * @param data 토큰에 담을 데이터
     * @param plusHour 만료 시간
     * @return 토큰 DTO
     */
    private TokenDTO createJwtToken(Map<String, Object> data, Long plusHour){

        var expiredLocalDateTime = LocalDateTime.now().plusHours(plusHour);
        var expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key)
                .claims(data)
                .expiration(expiredAt)
                .compact();

        return TokenDTO.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    /**
     * 토큰 유효성 검사
     * @param token 토큰
     * @return 토큰에 담긴 데이터
     * @throws ApiException 토큰이 유효하지 않을 때
     */
    public Map<String, Object> validationTokenWithThrow(String token) throws ApiException {

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parser()
                .verifyWith(key)
                .build();

        try{
            var result = parser.parseSignedClaims(token);
            return new HashMap<>(result.getPayload());
        }
        catch (SignatureException e){
            // 토큰이 유효하지 않을 때
            throw new ApiException(TokenErrorCode.INVALID_TOKEN, e);
        }
        catch (ExpiredJwtException e){
            // 토큰이 만료
            throw new ApiException(TokenErrorCode.EXPIRED_TOKEN, e);
        }
        catch (Exception e){
            throw new ApiException(TokenErrorCode.EXCEPTION_TOKEN, e);
        }
    }
}
