package co.kr.bongjae.db.mapper;

import co.kr.bongjae.db.model.user.UserEntity;
import co.kr.bongjae.db.model.user.enums.UserStatus;

import java.util.List;
import java.util.Optional;

/**
 * 사용자 매퍼
 * Mybatis
 */
public interface UserMapper {

    /**
     * 사용자 전체 조회
     * @return 사용자 엔티티 리스트
     */
    List<UserEntity> selectAllUser();

    /**
     * 사용자 조회
     * @return 사용자 엔티티
     */
    Optional<UserEntity> selectByIdAndStatus(Long id, UserStatus status);
}
