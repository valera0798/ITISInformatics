package database.dao;

import database.AbstractDAO;
import entity.Post;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAO extends AbstractDAO<Post, Integer> {
    private static PostDAO postDAO;

    public static final String TABLE_NAME = "posts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_OWNER_ID = "user_id";

    public static PostDAO getInstance(Connection connection) {
        if (postDAO == null) {
            postDAO = new PostDAO(connection);
        }
        return postDAO;
    }

    private PostDAO(Connection connection) {
        super(connection, TABLE_NAME, COLUMN_ID);
    }

    // CRUD
    public int create(Post post) {
        PreparedStatement preparedStatement = getPreparedStatement(createQuery());

        try {
            int columnIndex = 1;
            preparedStatement.setString(columnIndex++, post.getName());
            preparedStatement.setString(columnIndex++, post.getText());
            preparedStatement.setInt(columnIndex++, post.getOwnerId());

            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }
    public List<Post> getAll() {
        List<Post> posts = null;
        PreparedStatement preparedStatement = getPreparedStatement(getAllQuery());

        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            posts = getPosts(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return posts;
    }
    public Post getById(Integer id) {
        Post post = null;
        PreparedStatement preparedStatement = getPreparedStatement(getByIdQuery());

        try {
            int columnIndex = 1;
            preparedStatement.setInt(columnIndex++, post.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            post = getPost(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return post;
    }
    public void update(Post oldPost, Post newPost) {
        PreparedStatement preparedStatement = getPreparedStatement(updateQuery());
        try {
            int columnIndex = 1;
            // SET
            preparedStatement.setString(columnIndex++, newPost.getName());
            preparedStatement.setString(columnIndex++, newPost.getText());
            // WHERE
            preparedStatement.setInt(columnIndex++, oldPost.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    // SQL запросы
    public String createQuery() {
        return "INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_NAME + "," +
                COLUMN_TEXT + "," +
                COLUMN_OWNER_ID + ")" +
                " VALUES (?,?,?);";

    }
    public String updateQuery() {
        return "UPDATE " + TABLE_NAME +
                " SET " +
                COLUMN_NAME + " =? ," +
                COLUMN_TEXT + " =? " +
                " WHERE " +
                COLUMN_ID + " =?;";
    }
    public int deleteById(int key) {
        PreparedStatement preparedStatement = getPreparedStatement(deleteByIdQuery());
        int deletionIndicator = -1;
        try {
            int columnIndex = 1;
            preparedStatement.setInt(columnIndex++, key);

            deletionIndicator = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return deletionIndicator;
    }
    public String getPostOwnerQuery() {
        return "SELECT * FROM " + UserDAO.TABLE_NAME +
                " WHERE " + UserDAO.COLUMN_ID + " =?;";
    }
    private String getPostsFromLimitQuery() {
        return "SELECT * FROM " + TABLE_NAME +
                " ORDER BY " + COLUMN_ID + " DESC " +
                " LIMIT ? OFFSET ?;";
    }
    private String getPostsLikeQuery(String keyWord) {
        return "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_NAME + " LIKE '" + keyWord + "%' OR "
                            + COLUMN_TEXT + " LIKE '" + keyWord + "%';";
    }
    private String deleteByIdQuery() {
        return "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=?;";
    }

//-------------------------------------------Специфика Post-----------------------------------------------------------//
    private List<Post> getPosts(ResultSet resultSet) {
        List<Post> posts = new ArrayList<Post>();
        try {
            while (resultSet.next()) {
                posts.add(getPost(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    private Post getPost(ResultSet resultSet) {
        Post post = new Post();
        try {
            post.setId(Integer.valueOf(resultSet.getString(COLUMN_ID)));
            post.setName(resultSet.getString(COLUMN_NAME));
            post.setText(resultSet.getString(COLUMN_TEXT));
            post.setOwnerId(resultSet.getInt(COLUMN_OWNER_ID));
            post.setOwner(getPostOwner(post));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    private User getPostOwner(Post post) {
        User currentOwner = new User();
        PreparedStatement preparedStatement = getPreparedStatement(getPostOwnerQuery());

        try {
            int columnIndex = 1;
            preparedStatement.setInt(columnIndex++, post.getOwnerId());
            ResultSet ownerResultSet = preparedStatement.executeQuery();
            ownerResultSet.next();

            currentOwner.setId(ownerResultSet.getInt(UserDAO.COLUMN_ID));
            currentOwner.setEmail(ownerResultSet.getString(UserDAO.COLUMN_EMAIL));
            currentOwner.setPassword(ownerResultSet.getString(UserDAO.COLUMN_PASSWORD));
            currentOwner.setGender(ownerResultSet.getString(UserDAO.COLUMN_GENDER));
            currentOwner.setCountry(ownerResultSet.getString(UserDAO.COLUMN_COUNTRY));
            // TODO можно ли иначе?
            currentOwner.setPosts(getUserPosts(currentOwner));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentOwner;
    }

    public List<Post> getUserPosts(User owner) {
        List<Post> posts = new ArrayList<Post>();
        PreparedStatement preparedStatement = getPreparedStatement(UserDAO.getUserPostsQuery());

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
            currentPost.setId(resultSet.getInt(PostDAO.COLUMN_ID));
            currentPost.setName(resultSet.getString(PostDAO.COLUMN_NAME));
            currentPost.setText(resultSet.getString(PostDAO.COLUMN_TEXT));
            currentPost.setOwnerId(owner.getId());
            currentPost.setOwner(owner);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentPost;
    }

    public List<Post> getPosts(int startIndex, int limit) {
        List<Post> posts = new ArrayList<Post>();

        PreparedStatement preparedStatement = getPreparedStatement(getPostsFromLimitQuery());
        try {
            int columnIndex = 1;
            preparedStatement.setInt(columnIndex++, limit);
            preparedStatement.setInt(columnIndex++, startIndex);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(getPost(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    public List<Post> getPostsLike(String keyWord) {
        List<Post> posts = new ArrayList<Post>();

        PreparedStatement preparedStatement = getPreparedStatement(getPostsLikeQuery(keyWord));

        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                posts.add(getPost(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
}
