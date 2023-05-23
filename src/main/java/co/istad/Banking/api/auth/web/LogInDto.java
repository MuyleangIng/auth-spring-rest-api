package co.istad.Banking.api.auth.web;

import co.istad.Banking.api.user.validator.password.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogInDto(@Email
                       @NotBlank
                       String email,
                       @NotBlank
                       @Password
                       String password) {
}
