package co.kr.bongjae.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

/**
 * Request + Response 로깅
 * Filter로 한 이유는 Intercepter와 달리 요청 첫 단계에서 Request를 받고 응답 마지막 단계에서 Response를 받기 때문이다
 */
@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        log.info("------------------------------------------------------------------------");
        log.info("---->>>> init uri : {}", req.getRequestURI());

        chain.doFilter(req, res);

        // request 정보
        var headerNames = req.getHeaderNames();
        var headerValues = new StringBuilder();
        headerNames.asIterator().forEachRemaining(headerKey -> {
            var headerValue = req.getHeader(headerKey);
            headerValues.append("[ ").append(headerKey).append(" : ").append(headerValue).append(" ] , ");
        });

        var requestBody = new String(req.getContentAsByteArray());
        var uri = req.getRequestURI();
        var method = req.getMethod();

        log.info("---->>>> uri : {}, method : {}, header : {}, body : {}", uri, method, headerValues, requestBody);

        // response 정보
        var responseHeaderValues = new StringBuilder();
        res.getHeaderNames().forEach(headerKey -> {
            var headerValue = res.getHeader(headerKey);
            responseHeaderValues.append("[ ").append(headerKey).append(" : ").append(headerValue).append(" ] , ");
        });

        var responseBody = res.getContentAsByteArray().length > 0
                ? new String(res.getContentAsByteArray(), res.getCharacterEncoding())
                : "[Empty Body]";

        log.info("<<<<---- uri : {}, method : {}, header : {}, body : {}", uri, method, responseHeaderValues, responseBody);
        log.info("<<<<---- end uri");
        log.info("------------------------------------------------------------------------");

        res.copyBodyToResponse(); // 반드시 있어야지 데이터 담긴 상태로 응답됨.
    }
}