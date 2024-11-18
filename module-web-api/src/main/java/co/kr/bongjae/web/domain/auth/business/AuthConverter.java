package co.kr.bongjae.web.domain.auth.business;

import co.kr.bongjae.db.model.user.UserEntity;
import co.kr.bongjae.web.common.annotation.Converter;
import co.kr.bongjae.web.common.error.ErrorCode;
import co.kr.bongjae.web.common.exception.ApiException;
import co.kr.bongjae.web.domain.auth.model.UserRegisterRequestDTO;

import java.util.Optional;

@Converter // 컨버터임을 명시해줌
public class AuthConverter {

    public UserEntity toEntity(UserRegisterRequestDTO requestDto) {
        return Optional.ofNullable(requestDto)
                .map(x -> UserEntity.builder()
                        .name(x.getName())
                        .email(x.getEmail())
                        .password(x.getPassword())
                        .address(x.getAddress())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserRegisterRequestDTO NULL"));
    }
}
