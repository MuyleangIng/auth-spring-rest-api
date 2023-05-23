package co.istad.Banking.api.user.validator.roles;

import co.istad.Banking.api.user.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RoleIdConstraintValidator implements ConstraintValidator<RoleIdConstraint, List<Integer>> {


    private final UserMapper userMapper;
    @Override
    public boolean isValid(List<Integer> roleIds, ConstraintValidatorContext context) {
        for(Integer roleId : roleIds){
            if(!userMapper.checkRoleId(roleId)){
            return false;
            }

        }
        return true;
    }
}
