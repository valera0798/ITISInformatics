package util;

import entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static final String COOKIE_USER_ID = "COOKIE_USER_ID";
    public static final int COOKIE_EXPIRY = 60 * 60 * 24;   // 1 день

    public static void setUserId(HttpServletResponse httpServletResponse, User user) {
        Cookie userEmailCookie = new Cookie(COOKIE_USER_ID, String.valueOf(user.getId()));
        extendCookieMaxAge(userEmailCookie, COOKIE_EXPIRY);
        httpServletResponse.addCookie(userEmailCookie);
    }
    public static int getSavedUserId(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(COOKIE_USER_ID)) {
                    return Integer.valueOf(cookie.getValue());
                }
            }
        }
        return -1;
    }
    public static void deleteSavedUserId(HttpServletResponse httpServletResponse) {
        Cookie userEmailCookie = new Cookie(COOKIE_USER_ID, null);
        extendCookieMaxAge(userEmailCookie, 0);
        httpServletResponse.addCookie(userEmailCookie); // т.к cookie хранятся в виде множества то добавление объекта
                                                        // по существующему ключу заменит текущее значение
    }

    public static void extendCookieMaxAge(Cookie cookie, int expiry) {
        cookie.setMaxAge(expiry);
    }
}
