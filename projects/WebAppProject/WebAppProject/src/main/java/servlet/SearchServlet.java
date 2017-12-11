package servlet;

import entity.Post;
import servlet_filter.JDBCFilter;
import util.JDBC_connection.PostgreSQLServerConnectionUtil;
import util.ServletUtil;
import util.SessionUtil;
import util.dao.PostDAOUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "/search", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    public static final String JSP_DATA_POSTS = "/WEB-INF/jsp/data_posts.jsp";
    private PostDAOUtil postDAOUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        postDAOUtil = new PostDAOUtil(PostgreSQLServerConnectionUtil.getInstance());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyWord = request.getParameter("keyWord");
        keyWord = (keyWord == null) ? "" : keyWord;
        List<Post> posts = postDAOUtil.getPostsLike(keyWord);

        responsePosts(request, response, posts);
    }

    private void responsePosts(HttpServletRequest request, HttpServletResponse response, List<Post> posts) throws IOException, ServletException {
        request.setAttribute("posts", posts);
        request
                .getRequestDispatcher(JSP_DATA_POSTS)
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
