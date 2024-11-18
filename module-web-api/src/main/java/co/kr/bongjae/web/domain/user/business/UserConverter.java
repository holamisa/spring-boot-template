package co.kr.bongjae.web.domain.user.business;

import co.kr.bongjae.db.model.user.UserEntity;
import co.kr.bongjae.web.common.annotation.Converter;
import co.kr.bongjae.web.common.error.ErrorCode;
import co.kr.bongjae.web.common.exception.ApiException;
import co.kr.bongjae.web.domain.user.model.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter // 컨버터임을 명시해줌
public class UserConverter {

    public UserDTO toDto(UserEntity user) {
        return Optional.ofNullable(user)
                .map(x -> UserDTO.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .email(x.getEmail())
                        .address(x.getAddress())
                        .status(x.getStatus())
                        .registeredAt(x.getRegisteredAt())
                        .build())
                .orElse(null);
    }

    public List<UserDTO> toDto(
            List<UserEntity> userEntityList
    ){

        return userEntityList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public UserEntity toEntity(UserDTO userDTO) {
        return Optional.ofNullable(userDTO)
                .map(x -> UserEntity.builder()
                        .id(x.getId())
                        .name(x.getName())
                        .email(x.getEmail())
                        .address(x.getAddress())
                        .status(x.getStatus())
                        .registeredAt(x.getRegisteredAt())
                        .build())
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT, "UserDTO NULL"));
    }
}
