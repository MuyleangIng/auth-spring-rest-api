package co.istad.Banking.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

//    private final PasswordEncoder  encoder ;
    private final PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;
    //define in memory user
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        UserDetails user = User.builder()
//                .username("admin")
//                .password(encoder.encode("1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails golduser = User.builder()
//                .username("gold")
//                .password(encoder.encode("1"))
//                .roles("ACCOUNT")
//                .build();
//
//        userDetailsManager.createUser(user);
//        userDetailsManager.createUser(golduser);
//        return userDetailsManager;
//    }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider auth=new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);
        return auth;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //Disable csrf

        http.csrf(csrf -> csrf.disable());

        //Authorize URL mapping

//        http.authorizeHttpRequests(request ->{
//            request.requestMatchers("/api/v1/users/**").hasRole("SYSTEM");
//            request.requestMatchers("/api/v1/account-types/**","/api/v1/files/**").hasAnyRole("CUSTOMER","SYSTEM");
//            request.anyRequest().permitAll();
//
//        });


        http.authorizeHttpRequests(auth ->{
            auth.requestMatchers("/api/v1/auth/**").permitAll();
            auth.requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasAnyRole("ADMIN","SYSTEM");
            auth.requestMatchers(HttpMethod.POST,"/api/v1/users/**").hasRole("SYSTEM");
            auth.anyRequest().authenticated();

        });

        //Mechanism
        http.httpBasic();


        //Make security http STATELESS
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
