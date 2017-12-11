package util.JDBC_connection;

import javax.servlet.ServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionUtilInterface {
    Connection getConnection() throws SQLException, ClassNotFoundException;
    Connection getConnection(String hostName, String dataBaseName, String userName, String userPassword);
    void closeConnection(Connection connection);
    void rollbackConnection(Connection connection);

    void setConnection(ServletRequest servletRequest, Connection connection);
    Connection getStoredConnection(ServletRequest servletRequest);
}
