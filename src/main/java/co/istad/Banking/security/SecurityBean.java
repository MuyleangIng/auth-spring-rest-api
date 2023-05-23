package co.istad.Banking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBean {


    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
