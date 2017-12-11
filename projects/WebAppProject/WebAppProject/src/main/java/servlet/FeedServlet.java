package servlet;

import entity.Post;
import servlet_filter.JDBCFilter;
import util.JDBC_connection.PostgreSQLServerConnectionUtil;
import util.dao.PostDAOUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FeedServlet", urlPatterns = {JDBCFilter.FEED_SERVLET_PATTERN})
public class FeedServlet extends HttpServlet {
    public static final String JSP_FEED = "/WEB-INF/jsp/feed.jsp";

    public static final String ATTRIBUTE_POSTS = "posts";
    public static final int START_INDEX = 0;
    public static final int LIMIT = 10;

    private PostDAOUtil postDAOUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        postDAOUtil = new PostDAOUtil(PostgreSQLServerConnectionUtil.getInstance());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getAttribute(ATTRIBUTE_POSTS) == null) {
            boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
            if (ajax) {
                handleAjax(request, response);
            } else {
                handleRequest(request, response);
            }
        } else {
            request
                    .getRequestDispatcher(JSP_FEED)
                    .forward(request, response);
        }
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = postDAOUtil.getPosts(START_INDEX, LIMIT);
        request.setAttribute(ATTRIBUTE_POSTS, posts);

        request
                .getRequestDispatcher(JDBCFilter.FEED_SERVLET_PATTERN)
                .forward(request, response);
    }

    private void handleAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = postDAOUtil.getPosts(START_INDEX, LIMIT);
        request.setAttribute(ATTRIBUTE_POSTS, posts);

        request
                .getRequestDispatcher(JDBCFilter.FEED_SERVLET_PATTERN)
                .forward(request, response);
    }
}
