package servlet;

import util.JDBC_connection.PostgreSQLServerConnectionUtil;
import util.dao.PostDAOUtil;
import util.dao.UserDAOUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/add", urlPatterns = {"/add"})
public class AddPostServlet extends HttpServlet {
    public static final String PARAMETER_NAME = "name";
    public static final String PARAMETER_TEXT = "text";
    public static final String PARAMETER_USER_ID = "userId";

    private PostDAOUtil postDAOUtil;
    private UserDAOUtil userDAOUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        postDAOUtil = new PostDAOUtil(PostgreSQLServerConnectionUtil.getInstance());
        userDAOUtil = new UserDAOUtil(PostgreSQLServerConnectionUtil.getInstance());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        if (ajax) {
            String postName = request.getParameter(PARAMETER_NAME);
            String postText = request.getParameter(PARAMETER_TEXT);
            int userId = Integer.parseInt(request.getParameter(PARAMETER_USER_ID));

            if (postDAOUtil.createPost(postName, postText, userDAOUtil.getById(userId)) == 1) {
                responsePostCreated(response);
            } else {
                responsePostNotCreated(response);
            }
        }
    }

    private void responsePostCreated(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("SUCCESS");   // ответ на ajax-запрос
    }

    private void responsePostNotCreated(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("FAILURE");   // ответ на ajax-запрос
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
