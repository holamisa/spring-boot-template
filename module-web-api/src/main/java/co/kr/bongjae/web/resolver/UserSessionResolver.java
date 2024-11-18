package co.kr.bongjae.web.resolver;

import co.kr.bongjae.db.model.user.UserEntity;
import co.kr.bongjae.web.common.annotation.UserSession;
import co.kr.bongjae.web.domain.user.model.UserDTO;
import co.kr.bongjae.web.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

@Component
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class UserSessionResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 어노테이션 체크

        // 1. 어노테이션 있는지 체크
        var annotation = parameter.hasParameterAnnotation(UserSession.class);
        // 2. 파라미터의 타입 체크
        var parameterType = parameter.getParameterType().equals(UserDTO.class);

        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // support parameter 에서 true 반환 시 실행됨

        var requestContext = RequestContextHolder.getRequestAttributes();

        if (requestContext != null) {
            Object userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
            UserEntity userEntity = userService.getUserById(Long.parseLong(Objects.requireNonNull(userId).toString()), true);

            return UserDTO.builder()
                    .id(userEntity.getId())
                    .name(userEntity.getName())
                    .email(userEntity.getEmail())
                    .address(userEntity.getAddress())
                    .registeredAt(userEntity.getRegisteredAt())
                    .build();
        }

        return null;
    }
}