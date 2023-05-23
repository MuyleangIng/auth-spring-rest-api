package co.istad.Banking.api.auth;

import co.istad.Banking.api.auth.web.AuthDto;
import co.istad.Banking.api.auth.web.LogInDto;
import co.istad.Banking.api.auth.web.RegisterDto;


public interface AuthService {
    AuthDto login(LogInDto logInDto);
    void   register(RegisterDto registerDto);
    void verify(String email);
    void checkVerify(String email, String verifiedCode);

}
