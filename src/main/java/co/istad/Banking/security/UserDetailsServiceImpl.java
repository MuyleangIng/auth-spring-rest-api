package co.istad.Banking.security;

import co.istad.Banking.api.auth.AuthMapper;
import co.istad.Banking.api.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        User user= authMapper.loadUserByUsername(username).orElseThrow(()
                ->new ResponseStatusException(HttpStatus.NOT_FOUND,"User is in Valid"));
        System.out.println("ubiwecdbuivwui");

        CustomUserDetails customUserDetails=new CustomUserDetails();
        customUserDetails.setUser(user);
        return customUserDetails;
    }
}
