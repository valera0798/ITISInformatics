package servlet;

import entity.User;
import exception.EmailIsBusyException;
import servlet_filter.JDBCFilter;
import util.JDBC_connection.PostgreSQLServerConnectionUtil;
import util.ServletUtil;
import util.UserAuthenticationController;
import util.dao.UserDAOUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {
    public static final String HTML_REGISTRATION = "/WEB-INF/html/registration.html";

    // названия полей формы регистрации
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_PASSWORD_REPETITION = "password_repetition";
    public static final String PARAMETER_GENDER = "gender";
    public static final String PARAMETER_COUNTRY = "country";

    private UserDAOUtil userDAOUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAOUtil = new UserDAOUtil(PostgreSQLServerConnectionUtil.getInstance());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request
                .getRequestDispatcher(HTML_REGISTRATION)
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        if (ajax) {
            try {
                if (handleAjax(request, response))    // если содать нового пользователя получилось
                    // вернуть URL на переход по GET запросу в LoginServlet
                    responseUserCreated(response, ServletUtil.createRequestUri(request, "/login", "success"));
            } catch (EmailIsBusyException e) {
                // пользователь с данным email есть в базе
                responseUserNotCreated(response);
            }
        }
    }

    private boolean handleAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, EmailIsBusyException {
        String email = request.getParameter(PARAMETER_EMAIL);
        String password = request.getParameter(PARAMETER_PASSWORD);
        String gender = request.getParameter(PARAMETER_GENDER);
        String country = request.getParameter(PARAMETER_COUNTRY);
        User newUser = new User(email, password, gender, country);

        boolean successRegistration = doRegistration(newUser);
        if (successRegistration) {
            return true;
        } else
            throw new EmailIsBusyException();
    }

    private boolean doRegistration(User newUser) {
        return userDAOUtil.create(newUser) == 1;
    }

    private void responseUserCreated(HttpServletResponse response, String uri) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(uri);   // ответ на ajax-запрос
    }

    private void responseUserNotCreated(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("EMAILISBUSY");   // ответ на ajax-запрос
    }
}
