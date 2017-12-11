package util;

import entity.User;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static final String ATTRIBUTE_LOGGED_USER = "loggedUser";

    public static void setUser(HttpSession httpSession, User user) {
        httpSession.setAttribute(ATTRIBUTE_LOGGED_USER, user);
    }

    public static User getLoggedUser(HttpSession httpSession) {
        return (User) httpSession.getAttribute(ATTRIBUTE_LOGGED_USER);
    }
}
