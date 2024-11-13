package co.kr.bongjae.web.domain.user.business;

import co.kr.bongjae.web.common.annotation.Business;
import co.kr.bongjae.web.domain.user.model.UserDTO;
import co.kr.bongjae.web.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Business // 비즈니스 로직임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class UserBusiness {

    /**
     * 사용자 서비스
     */
    private final UserService userService;

    /**
     * 사용자 컨버터
     */
    private final UserConverter userConverter;

    /**
     * 사용자 조회
     * @param id 사용자 ID
     * @return 사용자 DTO
     */
    public UserDTO getUserById(Long id) {

        var userEntity = userService.getUserById(id);

        return userConverter.toDto(userEntity);
    }

    public UserDTO getUserByIdV2(Long id) {

        var userEntity = userService.getUserByIdV2(id);

        return userConverter.toDto(userEntity);
    }

    /**
     * 모든 사용자 조회
     * @return 사용자 DTO 리스트
     */
    public List<UserDTO> getAllUser(){
        var userEntities = userService.getAllUser();

        return userConverter.toDto(userEntities);
    }
}
