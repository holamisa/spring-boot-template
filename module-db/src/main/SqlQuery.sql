CREATE DATABASE IF NOT EXISTS `spring_practice`;

CREATE TABLE IF NOT EXISTS `spring_practice`.`user` (
`id` BIGINT(32) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL COMMENT '사용자 이름',
`email` VARCHAR(100) NOT NULL COMMENT '사용자 이메일',
`password` VARCHAR(100) NOT NULL COMMENT '비밀번호',
`status` VARCHAR(50) NOT NULL COMMENT '상태',
`address` varchar(150) NOT NULL COMMENT '집 주소',
`registered_at` DATETIME NULL COMMENT '등록 일자',
`unregistered_at` DATETIME NULL COMMENT '탈퇴 일자',
`last_login_at` DATETIME NULL COMMENT '마지막 로그인 시간',
PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '사용자';
