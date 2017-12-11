package util.dao;

import util.JDBC_connection.ConnectionUtilInterface;

public abstract class DAOUtil {
    protected ConnectionUtilInterface connectionUtilInterface;

    public DAOUtil(ConnectionUtilInterface connectionUtilInterface) {
        this.connectionUtilInterface = connectionUtilInterface;
    }
}
