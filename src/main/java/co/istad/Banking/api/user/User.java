package co.istad.Banking.api.user;


import co.istad.Banking.api.auth.Role;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class User {


    private Integer id;

    private String name;

    private String gender;

    private  String oneSignalId;

    private String studentCardId;

    private Boolean isStudent;

    private Boolean isDeleted;

    //Auth Feature info

    private String email;
    private String password;
    private Boolean isVerified;

    private String verifiedCode;

    // User has Roles
    private List<Role> roles;

}
