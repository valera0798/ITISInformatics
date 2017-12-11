package servlet;

import entity.Post;
import entity.User;
import exception.EmailIsBusyException;
import exception.NonexistentUserException;
import servlet_filter.JDBCFilter;
import util.JDBC_connection.PostgreSQLServerConnectionUtil;
import util.ServletUtil;
import util.SessionUtil;
import util.dao.UserDAOUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/user_profile", urlPatterns = {"/user_profile"})
public class UserServlet extends HttpServlet {
    public static final String JSP_USER = "/WEB-INF/jsp/user_profile.jsp";

    public static final String ATTRIBUTE_USER = "user";
    public static final String ATTRIBUTE_IS_USER_PAGE = "isUserPage";
    public static final String PARAMETER_ID = "id";

    private UserDAOUtil userDAOUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAOUtil = new UserDAOUtil(PostgreSQLServerConnectionUtil.getInstance());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int selectedUserId = Integer.valueOf(request.getParameter(PARAMETER_ID));
        boolean isUserPage = SessionUtil.getLoggedUser(request.getSession()).getId() == selectedUserId;
        User user = userDAOUtil.getById(selectedUserId);
        request.setAttribute(ATTRIBUTE_USER, user);
        request.setAttribute(FeedServlet.ATTRIBUTE_POSTS, user.getPosts());
        request.setAttribute(ATTRIBUTE_IS_USER_PAGE, isUserPage);
        request
                .getRequestDispatcher(JSP_USER)
                .forward(request, response);
    }
}
