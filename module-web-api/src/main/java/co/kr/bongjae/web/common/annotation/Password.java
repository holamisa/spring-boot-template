package co.kr.bongjae.web.common.annotation;

import co.kr.bongjae.web.validator.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {PasswordValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "비밀번호는 4~12자 사이여야 하며, 최소 하나의 대문자와 특수 문자를 포함해야 합니다.";

    // 4-12 문자, 최소 하나 대문자와 특수문자 포함
    String regexp() default "^(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{4,12}$";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}