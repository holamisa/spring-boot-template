package co.kr.bongjae.web.resolver;

import co.kr.bongjae.web.common.annotation.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class UserSessionResolver implements HandlerMethodArgumentResolver {

//    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 어노테이션 체크

        // 1. 어노테이션 있는지 체크
        var annotation = parameter.hasParameterAnnotation(UserSession.class);
        // 2. 파라미터의 타입 체크
        // var parameterType = parameter.getParameterType().equals(UserDTO.class);

//        return (annotation && parameterType);
        return (annotation);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // support parameter 에서 true 반환 시 실행됨

        var requestContext = RequestContextHolder.getRequestAttributes();

//        if (requestContext != null) {
//            var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
//            var userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));
//            return UserDTO.builder()
//                    .id(userEntity.getId())
//                    .name(userEntity.getName())
//                    .email(userEntity.getEmail())
//                    .status(userEntity.getStatus())
//                    .password(userEntity.getPassword())
//                    .address(userEntity.getAddress())
//                    .registeredAt(userEntity.getRegisteredAt())
//                    .unregisteredAt(userEntity.getUnregisteredAt())
//                    .lastLoginAt(userEntity.getLastLoginAt())
//                    .build();
//        }

        return null;
    }
}