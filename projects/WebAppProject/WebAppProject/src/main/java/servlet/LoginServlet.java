package servlet;

import entity.User;
import exception.NonexistentUserException;
import servlet_filter.JDBCFilter;
import util.*;
import util.JDBC_connection.PostgreSQLServerConnectionUtil;
import util.dao.UserDAOUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    public static final String HTML_LOGIN = "/WEB-INF/html/login.html";

    protected UserAuthenticationController userAuthenticationController;
    private UserDAOUtil userDAOUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAOUtil = new UserDAOUtil(PostgreSQLServerConnectionUtil.getInstance());
        userAuthenticationController = new UserAuthenticationController(userDAOUtil);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean requestFromAjax = request.getParameter("id") != null;   // проверка запроса на перевод к /feed
        if (requestFromAjax) {
            handleRequest(request, response,
                    userDAOUtil.getById(Integer.valueOf(request.getParameter("id"))));
            request
                    .getRequestDispatcher(ServletUtil.createRequestUri(request, JDBCFilter.FEED_SERVLET_PATTERN, request.getParameter("id")))
                    .forward(request, response);
        } else {                    // иначе переход на строки аутентификации
            check(request, response, "/feed");
        }
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        boolean isRememberMeSet = userAuthenticationController.isRememberMeSet(request);
        userAuthenticationController.register(request, response, user, isRememberMeSet);
    }

    public boolean check(HttpServletRequest request, HttpServletResponse response, String baseLocation) throws ServletException, IOException {
        boolean isRedirected = false;
        // до этого пользователь не был аутентифицирован
        if (SessionUtil.getLoggedUser(request.getSession()) == null) {
            if (CookieUtil.getSavedUserId(request) == -1) {
                forwardToLogin(request, response);
                isRedirected = true;
            } else {    // id пользователя находится в cookie
                User user = userDAOUtil.getById(CookieUtil.getSavedUserId(request));

                if (user != null) {
                    SessionUtil.setUser(request.getSession(), user);
                    forwardToFeed(request, response, baseLocation, user);
                    isRedirected = true;
                }
            }
        } else {
            if (CookieUtil.getSavedUserId(request) != SessionUtil.getLoggedUser(request.getSession()).getId()) {
                CookieUtil.setUserId(response, SessionUtil.getLoggedUser(request.getSession()));
            }
            forwardToFeed(request, response, baseLocation, SessionUtil.getLoggedUser(request.getSession()));
            isRedirected = true;
        }
        return isRedirected;
    }

    private void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(LoginServlet.HTML_LOGIN);
        dispatcher.forward(request, response);
    }
    private void forwardToFeed(HttpServletRequest request, HttpServletResponse response, String baseLocation, User user) throws ServletException, IOException {
        response.sendRedirect(ServletUtil.createRequestUri(
                request,
                baseLocation,
                user.getId()));
    }

//--------------------------------------------------------------------------------------------------------------------//
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        User user = null;

        if (ajax) {
            try {
                user = handleAjax(request);
                // вернуть URL на переход по GET запросу в LoginServlet
                boolean isRememberMeSet = userAuthenticationController.isRememberMeSet(request);
                userAuthenticationController.register(request, response, user, isRememberMeSet);
                responseUserExists(
                        response,
                        ServletUtil.createRequestUri(request, JDBCFilter.FEED_SERVLET_PATTERN, user.getId()));
            } catch (NonexistentUserException e) {
                // пользователь отсутсвует в базе
                responseUserNotExists(response);
            }
        }
    }

    private User handleAjax(HttpServletRequest request) throws ServletException, IOException, NonexistentUserException {
        String email = request.getParameter(RegistrationServlet.PARAMETER_EMAIL);
        String password = request.getParameter(RegistrationServlet.PARAMETER_PASSWORD);

        User user = userDAOUtil.getByEmailAndPassword(email, password);
        if (user != null)
            return user;
        else
            throw new NonexistentUserException();
    }

    private void responseUserExists(HttpServletResponse response, String uri) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(uri);   // ответ на ajax-запрос
    }

    private void responseUserNotExists(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("NONEXISTENT");   // ответ на ajax-запрос
    }
}
