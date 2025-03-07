package com.shakspotify.utils;

import android.util.Patterns;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    // Validates email using the built-in Android pattern
    public static boolean isValidEmail(String email) {
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    // Validates password:
    // - At least one lowercase letter (?=.*[a-z])
    // - At least one uppercase letter (?=.*[A-Z])
    // - At least one digit (?=.*\\d)
    // - At least one special character (you can adjust the list as needed) (?=.*[@#$%^&+=!])
    // - Minimum 8 characters in length .{8,}
    public static boolean isValidPassword(String password) {
        if (password == null) return false;
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@.#$%^~&+=!]).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
