package co.istad.Banking.api.auth;

import org.apache.ibatis.jdbc.SQL;

import java.lang.constant.Constable;

public class AuthProvider {
    private static final String tableName="users";
    public String buildRegisterSql(){
        return new SQL(){{
            INSERT_INTO(tableName);
            VALUES("email","#{u.email}");
            VALUES("password","#{u.password}");
            VALUES("is_verified","#{u.isVerified}");
            VALUES("is_deleted","FALSE");
        }}.toString();
    }


    public String buildCreateUserRoleSql(){
        return new SQL(){{
            INSERT_INTO("users_roles");
            VALUES("user_id","#{userId}");
            VALUES("role_id","#{roleId}");

        }}.toString();
    }



    public String  buildSelectEmailAndVerifiedCodeSql(){
        return new   SQL(){{
            SELECT("*");
            FROM(tableName);
            WHERE("email =#{email}","verified_code = #{verifiedCode}");
        }}.toString();
    }


    public String buildVerifySql(){
        return  new SQL(){{
            UPDATE(tableName);
            SET("is_verified= TRUE");
            SET("verified_code=NULL");
            WHERE("email =#{email}","verified_code = #{verifiedCode}");
        }}.toString();
    }


    public String buildUpdateVerifiedCodeSql(){
        return  new SQL(){{

            UPDATE(tableName);
            SET("verified_code= #{verifiedCode}");
            WHERE("email =#{email}");

        }}.toString();
    }

    public String buildLoadUserRolesSql(){
        return new SQL(){{
            SELECT("r.id,r.name");
            FROM("roles AS r");
            INNER_JOIN("users_roles as ur ON ur.role_id=r.id");
            WHERE("ur.user_id=#{id}");
        }}.toString();
    }


}
