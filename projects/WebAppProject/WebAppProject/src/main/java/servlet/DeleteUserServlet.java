package servlet;

import util.CookieUtil;
import util.JDBC_connection.PostgreSQLServerConnectionUtil;
import util.SessionUtil;
import util.dao.UserDAOUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/delete", urlPatterns = {"/delete"})
public class DeleteUserServlet extends HttpServlet {
    private UserDAOUtil userDAOUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAOUtil = new UserDAOUtil(PostgreSQLServerConnectionUtil.getInstance());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.valueOf(request.getParameter(UserServlet.PARAMETER_ID));
        if (userDAOUtil.deleteById(userId) == 1) {
            SessionUtil.setUser(request.getSession(), null);
            CookieUtil.deleteSavedUserId(response);
            getServletContext()
                    .getRequestDispatcher(LoginServlet.HTML_LOGIN)
                    .forward(request, response);
        }
    }
}
