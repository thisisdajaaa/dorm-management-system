package org.dms.utils;

import java.util.regex.Pattern;

public class StringUtil {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    public static boolean isMinValid(String input, int min) {
        return input != null && input.length() >= min;
    }

    public static boolean isMaxValid(String input, int max) {
        return input != null && input.length() <= max;
    }

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}

