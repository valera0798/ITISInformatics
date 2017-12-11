package util.dao;

import database.dao.PostDAO;
import database.dao.UserDAO;
import entity.Post;
import entity.User;
import util.JDBC_connection.ConnectionUtilInterface;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAOUtil extends DAOUtil{
    public PostDAOUtil(ConnectionUtilInterface connectionUtilInterface) {
        super(connectionUtilInterface);
    }

    public List<Post> getPosts(int startIndex, int limit) {
        Connection connection = null;
        List<Post> posts = new ArrayList<Post>();

        try {
            connection = connectionUtilInterface.getConnection();
            posts = PostDAO.getInstance(connection).getPosts(startIndex, limit);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public List<Post> getPostsLike(String keyWord) {
        Connection connection = null;
        List<Post> posts = new ArrayList<Post>();

        try {
            connection = connectionUtilInterface.getConnection();
            posts = PostDAO.getInstance(connection).getPostsLike(keyWord);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public int createPost(String postName, String postText, User owner) {
        Connection connection = null;
        int creationIndicator = -1;

        try {
            connection = connectionUtilInterface.getConnection();
            creationIndicator = PostDAO.getInstance(connection).create(
                    new Post(postName, postText, owner.getId(), owner)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return creationIndicator;
    }

    public int deletePostById(int id) {
        Connection connection = null;
        int deletionIndicator = -1;

        try {
            connection = connectionUtilInterface.getConnection();
            deletionIndicator = PostDAO.getInstance(connection).deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return deletionIndicator;
    }
}
