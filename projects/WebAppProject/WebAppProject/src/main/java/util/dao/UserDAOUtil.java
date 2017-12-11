package util.dao;

import database.dao.UserDAO;
import entity.User;
import util.JDBC_connection.ConnectionUtilInterface;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAOUtil extends DAOUtil {
    public UserDAOUtil(ConnectionUtilInterface connectionUtilInterface) {
        super(connectionUtilInterface);
    }

    public int create(User newUser) {
        Connection connection = null;
        int creationIndicator = -1;

        try {
            connection = connectionUtilInterface.getConnection();
            creationIndicator = UserDAO.getInstance(connection).create(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return creationIndicator;
    }

    public User getById(int id) {
        Connection connection = null;
        User user = null;

        try {
            connection = connectionUtilInterface.getConnection();
            user = UserDAO.getInstance(connection).getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getByEmail(String email) {
        Connection connection = null;
        User user = null;

        try {
            connection = connectionUtilInterface.getConnection();
            user = UserDAO.getInstance(connection).getByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getByEmailAndPassword(String email, String password) {
        Connection connection = null;
        User user = null;

        try {
            connection = connectionUtilInterface.getConnection();
            user = UserDAO.getInstance(connection).getByEmailAndPassword(email, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public int deleteById(int id) {
        Connection connection = null;
        int deletionIndicator = -1;

        try {
            connection = connectionUtilInterface.getConnection();
            deletionIndicator = UserDAO.getInstance(connection).deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return deletionIndicator;
    }

    public void update(User notChangedUser, User changedUser) {
        Connection connection = null;

        try {
            connection = connectionUtilInterface.getConnection();
            UserDAO.getInstance(connection).update(notChangedUser, changedUser);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
