package co.kr.bongjae.web.validator;

import co.kr.bongjae.web.common.annotation.Password;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * 비밀번호 검사
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private String regexp;

    @Override
    public void initialize(Password constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Pattern.matches(regexp, value);
    }
}