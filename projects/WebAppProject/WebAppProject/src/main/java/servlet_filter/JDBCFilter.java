package servlet_filter;

import util.JDBC_connection.ConnectionUtil;
import util.JDBC_connection.PostgreSQLServerConnectionUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCFilter implements Filter {
    public static final String WELCOME_PATTERN = "/";
    public static final String REGISTRATION_SERVLET_PATTERN = "/registration";
    public static final String LOGIN_SERVLET_PATTERN = "/login";
    public static final String USER_SERVLET_PATTERN = "/user";
    public static final String FEED_SERVLET_PATTERN = "/feed";

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = ((HttpServletRequest) req); // преобразование типа для получения пути сервлета

        String servletPath = httpServletRequest.getServletPath();

        if (servletPath.contains(REGISTRATION_SERVLET_PATTERN) ||
                servletPath.contains(LOGIN_SERVLET_PATTERN) ||
                servletPath.contains(FEED_SERVLET_PATTERN) ||
                servletPath.contains(USER_SERVLET_PATTERN)) {
            Connection connection = null;
            ConnectionUtil connectionUtil = PostgreSQLServerConnectionUtil.getInstance();
            try{
                connection = connectionUtil.getConnection();
                //connection.setAutoCommit(false);    // самостоятельное управление транзакциями

                connectionUtil.setConnection(req, connection);

                chain.doFilter(req, resp);
                //connection.commit();
            } catch (SQLException e) {
                connectionUtil.rollbackConnection(connection);
                e.printStackTrace();
                connectionUtil.closeConnection(connection);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                connectionUtil.closeConnection(connection);
            }
        }
    }

    public void destroy() {
    }
}
