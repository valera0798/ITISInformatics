package database;

import database.dao.UserDAO;
import entity.EntityInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<Entity extends EntityInterface, Key> implements DAOInterface<Entity, Key> {
    private Connection connection;

    protected String tableName;
    protected String columnId;

    public AbstractDAO(Connection connection, String tableName, String columnId) {
        this.connection = connection;
        this.tableName = tableName;
        this.columnId = columnId;
    }

    // CRUD
    public abstract int create(Entity entity);
    public abstract List<Entity> getAll();
    public abstract Entity getById(Key id);
    public abstract void update(Entity oldEntity, Entity newEntity);
    public void delete(Entity entity) {
        PreparedStatement preparedStatement = getPreparedStatement(deleteQuery());
        try {
            int columnIndex = 1;
            preparedStatement.setInt(columnIndex++, entity.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    // получение SQL запросов для реализации CRUD
    public abstract String createQuery();
    public String getAllQuery() {
        return "SELECT * FROM " + tableName;
    }
    public String getByIdQuery() {
        return "SELECT * FROM " + tableName +
                " WHERE " + columnId + " =?;";
    }
    public abstract String updateQuery();
    public String deleteQuery() {
        return "DELETE FROM " + tableName +
                " WHERE " + columnId + " =?;";
    }

    // JDBC специфика
    public PreparedStatement getPreparedStatement(String sqlStatement) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }
    public void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
