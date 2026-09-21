package todo.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import todo.Security.filter.UserAuthentication;
import todo.common.exception.UnprocessableException;

import java.security.SecureRandom;

public final class CommonUtils {

    public static int generateRandomInt(int length) {
        SecureRandom random = new SecureRandom();
        String alphabet = "0123456789";
        StringBuilder returnValue = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char c = alphabet.charAt(random.nextInt(alphabet.length()));
            if (i == 0 && c == '0') {
                c = alphabet.substring(1).charAt(random.nextInt(9));
            }
            returnValue.append(c);
        }
        return Integer.parseInt(returnValue.toString());
    }

    public static String generateUserId(int id) {
        return String.format("%03d", id);
    }

    public static UserAuthentication getUserAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UserAuthentication) {
            return (UserAuthentication) authentication;
        } else {
            throw new UnprocessableException("unauthorized user");
        }
    }
}
