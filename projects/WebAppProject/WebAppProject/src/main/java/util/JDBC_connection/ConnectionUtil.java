package util.JDBC_connection;

import javax.servlet.ServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConnectionUtil implements ConnectionUtilInterface {
    private static final String ATTRIBUTE_CONNECTION = "ATTRIBUTE_CONNECTION";

    static ConnectionUtil connectionUtil;

    private String driver;                 // драйвер подключения
    // URL
    private String scheme;                 // схема доступа к ресурсам
    private String hostName;               // локальный хост
    private String port;                   // порт драйвера
    private String databaseName;
    // Authority
    private String userName;               // данные авторизации на базе
    private String userPassword;

    private Connection connection;

    ConnectionUtil(String driver, String scheme, String hostName, String port, String databaseName, String userName, String userPassword) {
        this.driver = driver;
        this.scheme = scheme;
        this.hostName = hostName;
        this.port = port;
        this.databaseName = databaseName;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        return getConnection(hostName, databaseName, userName, userPassword);
    }
    public Connection getConnection(String hostName, String dataBaseName, String userName, String userPassword) {
        if (connection == null) {
            try {
                Class.forName(driver);    // явная загрузка класса драйвера

                String connectionURL = scheme + hostName + port + dataBaseName;
                connection = DriverManager.getConnection(connectionURL, userName, userPassword);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (connection.isClosed()) {
                    String connectionURL = scheme + hostName + port + dataBaseName;
                    connection = DriverManager.getConnection(connectionURL, userName, userPassword);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void rollbackConnection(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setConnection(ServletRequest servletRequest, Connection connection) {
        servletRequest.setAttribute(ATTRIBUTE_CONNECTION, connection);
    }
    public Connection getStoredConnection(ServletRequest servletRequest) {
        return (Connection) servletRequest.getAttribute(ATTRIBUTE_CONNECTION);
    }
}
