package co.istad.Banking.api.user.validator.email;

import co.istad.Banking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailUniqueConstraintsValidator implements ConstraintValidator<EmailUnique,String> {

    private final UserMapper userMapper;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userMapper.existByEmail(email);
    }
}
