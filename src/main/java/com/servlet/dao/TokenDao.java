package com.servlet.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenDao {

    private static Map<String, String> userTokens = new HashMap<>();
    private static Map<String, String> userPasswords = new HashMap<>();

    static {
        userPasswords.put("joao", "123");
        userPasswords.put("ana", "456");
        userPasswords.put("admin", "admin");
    }

    public static String findUser(String token) {
        return userTokens.get(token);
    }

    public static void removeToken(String token) {
        userTokens.remove(token);
    }

    public static String saveUserToken(String token, String user) {
        return userTokens.put(token, user);
    }

    public static String findUserPassword(String user) {
        return userPasswords.get(user);
    }

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}
