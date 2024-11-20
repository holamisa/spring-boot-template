package co.kr.bongjae.web.interceptor;

import co.kr.bongjae.web.common.error.TokenErrorCode;
import co.kr.bongjae.web.common.exception.ApiException;
import co.kr.bongjae.web.domain.token.service.AuthType;
import co.kr.bongjae.web.domain.token.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

@Component
@Slf4j // 로깅
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        // WEB, chrome 의 경우 GET, POST OPTIONS = pass
        if(HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;
        }

        // js, html, png등 resource 를 요청하는 경우 = pass
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // header 검증
        var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authorization == null){
            throw new ApiException(TokenErrorCode.NOT_FOUND_TOKEN);
        }
        log.info("authorization : {}", authorization);
        var accessToken = tokenService.getAuthorizationFromHeader(authorization, AuthType.BEARER);
        log.info("accessToken : {}", accessToken);

        if(accessToken == null){
            throw new ApiException(TokenErrorCode.NOT_FOUND_TOKEN);
        }

        var userId = tokenService.validationToken(accessToken);

        if(userId != null){
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);

            return true;
        }

        throw new ApiException(TokenErrorCode.AUTHORIZATION_FAILURE);

//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
