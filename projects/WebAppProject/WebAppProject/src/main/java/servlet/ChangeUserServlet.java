package servlet;

import entity.User;
import util.CookieUtil;
import util.JDBC_connection.PostgreSQLServerConnectionUtil;
import util.SessionUtil;
import util.dao.UserDAOUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "/change", urlPatterns = {"/change"})
public class ChangeUserServlet extends HttpServlet {
    public static final String PARAMETER_CHANGES = "changes";

    private UserDAOUtil userDAOUtil;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAOUtil = new UserDAOUtil(PostgreSQLServerConnectionUtil.getInstance());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User notChangedUser = SessionUtil.getLoggedUser(request.getSession());
        if (notChangedUser.getPassword().equals(getEnteredPassword(request.getParameter(PARAMETER_CHANGES)))) {
            String s = request.getParameter(PARAMETER_CHANGES);
            User changedUser = getChangedUser(request.getParameter(PARAMETER_CHANGES), notChangedUser);

            if (userDAOUtil.getByEmail(changedUser.getEmail()) == null ||
                    changedUser.getEmail().equals(notChangedUser.getEmail())) {
                userDAOUtil.update(notChangedUser, changedUser);
                SessionUtil.setUser(request.getSession(), changedUser);
                responseUserChanged(response);
            } else {
                responseEmailIsUsedAlready(response);
            }
        } else {
            responseOldEmailIsWrong(response);
        }
    }

    private String getEnteredPassword(String parameter) {
        String[] newParameters = parameter.split("&");
        for (String newParameter : newParameters) {
            if (newParameter.contains("old-password"))
                return newParameter.split("=")[1];
        }
        return null;
    }

    private User getChangedUser(String parameter, User notChangedUser) {
        String[] userParameters = parameter.split("&");
        User changedUser = new User();
        changedUser.setId(notChangedUser.getId());
        if (userParameters[0].split("=")[1].equals("default")) {
            changedUser.setEmail(notChangedUser.getEmail());
        } else {
            changedUser.setEmail(userParameters[0].split("=")[1].replace("%40", "@"));
        }
        changedUser.setPassword(userParameters[2].split("=")[1]);
        changedUser.setGender(userParameters[3].split("=")[1]);
        changedUser.setCountry(userParameters[4].split("=")[1]);
        changedUser.setPosts(notChangedUser.getPosts());

        return changedUser;
    }

    private void responseUserChanged(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("SUCCESS");   // ответ на ajax-запрос
    }
    private void responseEmailIsUsedAlready(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("BAD_EMAIL");   // ответ на ajax-запрос
    }
    private void responseOldEmailIsWrong(HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("BAD_OLD_PASSWORD");   // ответ на ajax-запрос
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
