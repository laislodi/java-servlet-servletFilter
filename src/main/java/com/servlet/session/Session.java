package com.servlet.session;

public class Session {

    private static ThreadLocal<String> token = new ThreadLocal<>();
    private static ThreadLocal<String> user = new ThreadLocal<>();

    /**
     * Get the token from the Session
     */
    public static String getToken() {
        return token.get();
    }

    /**
     * Set the token from the Session
     */
    public static void setToken(String pToken) {
        token.set(pToken);
    }

    /**
     * Get the user from the Session
     */
    public static String getUser() {
        return user.get();
    }

    /**
     * Set the user from the Session
     */
    public static void setUser(String pUser) {
        user.set(pUser);
    }
}
