package servlet;

import util.JDBC_connection.PostgreSQLServerConnectionUtil;
import util.dao.PostDAOUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/delete_post", urlPatterns = {"/delete_post"})
public class DeletePostServlet extends HttpServlet {
    public static final String PARAMETER_POST_ID = "postId";

    private PostDAOUtil postDAOUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        postDAOUtil = new PostDAOUtil(PostgreSQLServerConnectionUtil.getInstance());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String postId = request.getParameter(PARAMETER_POST_ID);
        int id = Integer.valueOf(postId);

        if (postDAOUtil.deletePostById(id) == 1) {
            responseDeletionSuccessful(response);
        } else {
            responseDeletionFailed(response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void responseDeletionSuccessful(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("SUCCESS");   // ответ на ajax-запрос
    }

    private void responseDeletionFailed(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("FAILURE");   // ответ на ajax-запрос
    }
}
