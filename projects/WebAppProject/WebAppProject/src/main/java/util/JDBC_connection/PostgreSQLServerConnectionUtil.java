package util.JDBC_connection;

/*
    JDBC: API-механизм подключения к базе данных дял последующего взаимодействия
*/

public class PostgreSQLServerConnectionUtil extends ConnectionUtil {
    private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    // URL
    private static final String SCHEMA = "jdbc:postgresql://";      // схема доступа к ресурсам (postgresql)
    private static final String HOST_NAME = "localhost";             // локальный хост
    private static final String PORT = ":5432/";                    // порт драйвера (postgresql)
    private static final String DATA_BASE_NAME = "webappproject";
    // Authority
    private static final String USER_NAME = "postgres";              // данные авторизации на базе
    private static final String USER_PASSWORD = "21797";

    private PostgreSQLServerConnectionUtil() {
        super(POSTGRESQL_DRIVER,
                SCHEMA,
                HOST_NAME,
                PORT,
                DATA_BASE_NAME,
                USER_NAME, USER_PASSWORD);
    }

    public static ConnectionUtil getInstance() {
        if (connectionUtil == null) {
            connectionUtil = new PostgreSQLServerConnectionUtil();
        }
        return connectionUtil;
    }
}
