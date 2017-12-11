package util;

import entity.User;
import servlet.LoginServlet;
import servlet_filter.JDBCFilter;
import util.dao.UserDAOUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserAuthenticationController {
    private UserDAOUtil userDAOUtil;

    public UserAuthenticationController(UserDAOUtil userDAOUtil) {
        this.userDAOUtil = userDAOUtil;
    }

    public void register(HttpServletRequest request, HttpServletResponse response, User user, boolean isRememberMeSet) {
        HttpSession currentHttpSession = request.getSession();
        SessionUtil.setUser(currentHttpSession, user);

        if (isRememberMeSet) {
            CookieUtil.setUserId(response, user);
        } else {
            CookieUtil.deleteSavedUserId(response);
        }
    }

    public boolean isRememberMeSet(HttpServletRequest request) {
        String rememberMe = request.getParameter("rememberMe");
        return "yes".equals(rememberMe);
    }
}
