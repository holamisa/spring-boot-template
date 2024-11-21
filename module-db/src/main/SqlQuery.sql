CREATE DATABASE IF NOT EXISTS `spring_practice`;

CREATE TABLE IF NOT EXISTS `spring_practice`.`user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '사용자 이름',
  `email` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '사용자 이메일',
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '비밀번호',
  `status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '상태',
  `address` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '집 주소',
  `registered_at` datetime(3) NOT NULL COMMENT '등록 일자',
  `unregistered_at` datetime(3) DEFAULT NULL COMMENT '탈퇴 일자',
  `last_login_at` datetime(3) DEFAULT NULL COMMENT '마지막 로그인 시간',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='사용자';


CREATE TABLE IF NOT EXISTS `file` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '파일 명',
  `original_name` varchar(100) DEFAULT NULL COMMENT '원본 파일 명',
  `path_url` varchar(500) NOT NULL COMMENT '파일 URL',
  `size` int unsigned DEFAULT NULL COMMENT '파일 사이즈',
  `extension` varchar(20) NOT NULL COMMENT '파일 확장자',
  `status` varchar(50) DEFAULT NULL COMMENT '파일 상태',
  `registered_at` datetime(3) NOT NULL COMMENT '생성 일자',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='파일';