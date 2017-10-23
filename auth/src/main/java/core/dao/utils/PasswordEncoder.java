package core.dao.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static core.dao.utils.Settings.SALT;

public class PasswordEncoder {

    private static String md5(String password) {
        String result = null;
        try {
            byte[] passwordBytes = password.getBytes();
            byte[] encodedPassword;
            MessageDigest md = MessageDigest.getInstance("MD5");
            encodedPassword = md.digest(passwordBytes);
            result = new String(encodedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String encode(String password) {
        String result = md5(password) + SALT;
        result = new StringBuilder(result).reverse().toString();
        result = md5(result);
        return result;
    }
}
