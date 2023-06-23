package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static props.ConfigurationManager.config;

/**
 * The type Data base connection.
 */
public final class DataBaseConnection {

    /**
     * Create connection to database.
     * @return the connection
     * @throws SQLException the sql exception
     */
    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(config().url(),
                config().user(), config().password());
    }

    private DataBaseConnection() {
        throw new IllegalStateException("Utility class");
    }
}
