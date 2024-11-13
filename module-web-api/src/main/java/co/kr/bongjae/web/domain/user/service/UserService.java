package co.kr.bongjae.web.domain.user.service;

import co.kr.bongjae.db.jpa.UserRepository;
import co.kr.bongjae.db.mapper.UserMapper;
import co.kr.bongjae.db.model.user.UserEntity;
import co.kr.bongjae.db.model.user.enums.UserStatus;
import co.kr.bongjae.web.common.error.UserErrorCode;
import co.kr.bongjae.web.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 서비스 로직임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class UserService {

    /**
     * 사용자 레포지토리
     * JPA
     */
    private final UserRepository userRepository;

    /**
     * 사용자 매퍼
     * MyBatis
     */
    private final UserMapper userMapper;

    /**
     * 사용자 ID로 조회
     * @param id 사용자 ID
     * @param throwNull NULL일 경우 예외 발생 여부
     * @return 사용자 엔티티
     */
    public UserEntity getUserById(Long id, Boolean throwNull) {
        if (throwNull != null && throwNull) {
            return userRepository.findByIdAndStatus(id, UserStatus.REGISTERED)
                    .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
        } else {
            return userRepository.findByIdAndStatus(id, UserStatus.REGISTERED).orElse(null);
        }
    }

    /**
     * 사용자 ID로 조회
     * @param id 사용자 ID
     * @return 사용자 엔티티
     */
    public UserEntity getUserById(Long id) {
        return getUserById(id, true);
    }

    /**
     * 사용자 전체 조회
     * @return 사용자 엔티티 리스트
     */
    public List<UserEntity> getAllUser() {
        return userMapper.selectAllUser();
    }

    public UserEntity getUserByIdV2(Long id, Boolean throwNull) {
        if (throwNull != null && throwNull) {
            return userMapper.selectByIdAndStatus(id, UserStatus.REGISTERED)
                    .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
        } else {
            return userMapper.selectByIdAndStatus(id, UserStatus.REGISTERED).orElse(null);
        }
    }

    public UserEntity getUserByIdV2(Long id) {
        return getUserByIdV2(id, true);
    }
}
