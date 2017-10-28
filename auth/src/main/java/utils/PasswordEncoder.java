package utils;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static utils.Settings.SALT;

public class PasswordEncoder {

    private static Logger logger = Logger.getLogger(PasswordEncoder.class);

    private static String md5(String password) {
        String result = null;
        try {
            byte[] passwordBytes = password.getBytes("UTF-8");
            byte[] encodedPassword;
            MessageDigest md = MessageDigest.getInstance("MD5");
            encodedPassword = md.digest(passwordBytes);
            result = new String(encodedPassword, "UTF-8");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            //TODO rethrow exception
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
