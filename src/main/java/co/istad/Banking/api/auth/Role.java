package co.istad.Banking.api.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    private Integer id;
    private  String name;


    @Override
    public String getAuthority() {
        return  "ROLE_" + name;
    }
}
