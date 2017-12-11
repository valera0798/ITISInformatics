package servlet;

import servlet_filter.JDBCFilter;
import util.CookieUtil;
import util.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/logout", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionUtil.setUser(request.getSession(), null);
        CookieUtil.deleteSavedUserId(response);
        response
                .sendRedirect(JDBCFilter.LOGIN_SERVLET_PATTERN);
    }
}
