package co.kr.bongjae.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
@Slf4j // 로깅
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class AuthorizationInterceptor implements HandlerInterceptor {

//    private final TokenBusiness tokenBusiness;

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
        var accessToken = request.getHeader("authorization-token");
        log.info("accessToken : {}", accessToken);
        return true;
//        if(accessToken == null){
//            throw new ApiException(TokenErrorCode.NOT_FOUND_TOKEN);
//        }
//        var userId = tokenBusiness.validationToken(accessToken);
//
//        if(userId != null){
//            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
//            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
//
//            return true;
//        }

//        throw new ApiException(TokenErrorCode.AUTHORIZATION_FAILURE);

////        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
