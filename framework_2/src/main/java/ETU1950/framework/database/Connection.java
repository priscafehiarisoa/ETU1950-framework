package database;

import database.core.Config;
import database.core.DBConnection;
import database.core.Database;

import java.sql.SQLException;

public class Connection {

    public static DBConnection getConnection() throws SQLException {
        Database data= Config.getPgDb();
        return data.createConnection();
    }

    public static void safe_close_connection(DBConnection dbConnection) throws SQLException {
        dbConnection.commit();
        dbConnection.close();
    }
}
