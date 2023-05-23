package co.istad.Banking.api.auth.web;

import co.istad.Banking.api.auth.AuthService;
import co.istad.Banking.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthResController {


    private  final AuthService authService;

    @PostMapping("/login")
    public BaseRest<?> login(@Valid @RequestBody LogInDto logInDto){

        AuthDto authDto = authService.login(logInDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You Have Been login Successfully")
                .timestamp(LocalDateTime.now())
                .data(authDto)
                .build();
    }

    @PostMapping("/register")
    public BaseRest<?> register(@Valid @RequestBody RegisterDto registerDto ){
    //Call service
        authService.register(registerDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You Have registered Successfully")
                .timestamp(LocalDateTime.now())
                .data(registerDto.email())
                .build();
    }


    @PostMapping("/verify")
    public BaseRest<?> verify(@RequestParam String email){
        authService.verify(email);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Please Check your Email !!")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }

    @GetMapping("/check-verify")
    public  BaseRest<?> verifiedCode(@RequestParam String email,
                                    @RequestParam String verifiedCode){

        authService.checkVerify(email,verifiedCode);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been Verified SuccessFully !")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }


}
