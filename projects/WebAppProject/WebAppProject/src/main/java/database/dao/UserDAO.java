package database.dao;

import javax.annotation.Nullable;
import database.AbstractDAO;
import entity.Post;
import entity.User;
import exception.NonexistentUserException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends AbstractDAO<User, Integer> {
    private static UserDAO userDAO;

    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_COUNTRY = "country";

    public static UserDAO getInstance(Connection connection) {
        if (userDAO == null) {
            userDAO = new UserDAO(connection);
        }
        return userDAO;
    }

    private UserDAO(Connection connection) {
        super(connection, TABLE_NAME, COLUMN_ID);
    }

    // CRUD
    public int create(User user) {
        PreparedStatement preparedStatement = getPreparedStatement(createQuery());

        try {
            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, user.getEmail());
            preparedStatement.setString(columnIndex++, user.getPassword());
            preparedStatement.setString(columnIndex++, user.getGender());
            preparedStatement.setString(columnIndex++, user.getCountry());

            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }
    public List<User> getAll() {
        List<User> users = null;
        PreparedStatement preparedStatement = getPreparedStatement(getAllQuery());

        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            users = getUsers(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return users;
    }
    @Nullable
    public User getById(Integer id) {
        User user = null;
        PreparedStatement preparedStatement = getPreparedStatement(getByIdQuery());

        try {
            int columnIndex = 1;
            preparedStatement.setInt(columnIndex++, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = getUser(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NonexistentUserException e) {
            return null;
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return user;
    }
    @Nullable
    public User getByEmailAndPassword(String email, String password) {
        User user = null;
        PreparedStatement preparedStatement = getPreparedStatement(getByEmailAndPasswordQuery());

        try {
            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, email);
            preparedStatement.setString(columnIndex++, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = getUser(resultSet);
        } catch (SQLException e) {
            e.getMessage();
        } catch (NonexistentUserException e) {
            return null;
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return user;
    }
    @Nullable
    public User getByEmail(String email) {
        User user = null;
        PreparedStatement preparedStatement = getPreparedStatement(getByEmailQuery());

        try {
            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user = getUser(resultSet);
        } catch (SQLException e) {
            e.getMessage();
        } catch (NonexistentUserException e) {
            return null;
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return user;
    }
    public void update(User oldUser, User newUser) {
        PreparedStatement preparedStatement = getPreparedStatement(updateQuery());
        try {
            int columnIndex = 1;
            // SET
            preparedStatement.setString(columnIndex++, newUser.getEmail());
            preparedStatement.setString(columnIndex++, newUser.getPassword());
            preparedStatement.setString(columnIndex++, newUser.getGender());
            preparedStatement.setString(columnIndex++, newUser.getCountry());
            // WHERE
            preparedStatement.setInt(columnIndex++, oldUser.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }
    public int deleteById(int id) {
        PreparedStatement preparedStatement = getPreparedStatement(deleteByIdQuery());
        int deletionIndicator = -1;
        try {
            int columnIndex = 1;
            preparedStatement.setInt(columnIndex++, id);

            deletionIndicator = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return deletionIndicator;
    }

    // SQL запросы
    public String createQuery() {
        return "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_EMAIL + "," +
                COLUMN_PASSWORD + "," +
                COLUMN_GENDER + "," +
                COLUMN_COUNTRY + ")" +
                " VALUES (?,?,?,?);";

    }
    public String updateQuery() {
        return "UPDATE " + TABLE_NAME +
                " SET " +
                COLUMN_EMAIL + " =? ," +
                COLUMN_PASSWORD + " =? ," +
                COLUMN_GENDER + " =? ," +
                COLUMN_COUNTRY + " =? " +
                " WHERE " +
                columnId + " =?;";
    }
    public static String getUserPostsQuery() {
        return "SELECT * FROM " + TABLE_NAME +
                " INNER JOIN " + PostDAO.TABLE_NAME +
                " ON " + TABLE_NAME + "." + COLUMN_ID + " = " + PostDAO.TABLE_NAME + "." + PostDAO.COLUMN_OWNER_ID +
                " WHERE " + TABLE_NAME + "." + COLUMN_ID + " =?;";
    }
    public String getByEmailAndPasswordQuery() {
        return "SELECT * FROM " + tableName +
                " WHERE " + COLUMN_EMAIL + " =? AND " +
                    COLUMN_PASSWORD + " =?;";
    }
    public String getByEmailQuery() {
        return "SELECT * FROM " + tableName +
                " WHERE " + COLUMN_EMAIL + " =?;";
    }
    private String deleteByIdQuery() {
        return "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?;";
    }

//-------------------------------------------Специфика User-----------------------------------------------------------//
    private List<User> getUsers(ResultSet resultSet) {
        List<User> users = new ArrayList<User>();
        try {
            while (resultSet.next()) {
                users.add(getUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NonexistentUserException e) {
            e.getMessage();
        }
        return users;
    }

    private User getUser(ResultSet resultSet) throws NonexistentUserException {
        User user = new User();
        try {
            user.setId(Integer.valueOf(resultSet.getInt(COLUMN_ID)));
            user.setEmail(resultSet.getString(COLUMN_EMAIL));
            user.setPassword(resultSet.getString(COLUMN_PASSWORD));
            user.setGender(resultSet.getString(COLUMN_GENDER));
            user.setCountry(resultSet.getString(COLUMN_COUNTRY));
            user.setPosts(getUserPosts(user));
        } catch (SQLException e) {
            throw new NonexistentUserException();
        }
        return user;
    }

    public List<Post> getUserPosts(User owner) {
        List<Post> posts = new ArrayList<Post>();
        PreparedStatement preparedStatement = getPreparedStatement(getUserPostsQuery());

        try {
            int columnIndex = 1;
            preparedStatement.setInt(columnIndex++, owner.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(getPost(owner, resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    private Post getPost(User owner, ResultSet resultSet) {
        Post currentPost = new Post();
        try {
            String postId = resultSet.getString(6);
            // TODO how to get value by complex attribute name?
            currentPost.setId(Integer.parseInt(postId));
            currentPost.setName(resultSet.getString(PostDAO.COLUMN_NAME));
            currentPost.setText(resultSet.getString(PostDAO.COLUMN_TEXT));
            currentPost.setOwnerId(owner.getId());
            currentPost.setOwner(owner);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentPost;
    }
}
