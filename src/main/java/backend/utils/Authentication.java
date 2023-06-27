package backend.utils;

import backend.database.DatabaseController;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.sql.SQLException;

public class Authentication {
    private static Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);

    public static boolean password_authentication(Integer userId, String password, String table) throws SQLException {
        String db_password = DatabaseController.getUserPassword(userId, table);
        return encoder.matches(password, db_password);
    }

    public static String hash_password(String password){
        return encoder.encode(password);
    }

}
