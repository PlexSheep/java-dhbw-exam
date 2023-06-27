package backend.utils;

import backend.database.DatabaseController;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.sql.SQLException;

public class Authentication {
    private static Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);

    /**
     * Function to handle user authentication with the database
     * @param userId
     * @param password
     * @param table
     * @return
     * @throws SQLException
     */
    public static boolean password_authentication(Integer userId, String password, String table) throws SQLException {
        String db_password = DatabaseController.get_user_password(userId, table);
        return encoder.matches(password, db_password);
    }
    /**
     * Function to hash the password
     * @param password
     * @return
     */
    public static String hash_password(String password){
        return encoder.encode(password);
    }

}
