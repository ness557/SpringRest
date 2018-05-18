package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnector {

    private String url;
    private String driver;
    private Properties properties;
    private Connection connection;

    public JdbcConnector(String url, String driver, String username, String password) {

        properties = new Properties();
        this.url = url;
        this.driver = driver;
        properties.setProperty("user", username);
        properties.setProperty("password", password);
    }


    public Connection getConnection() {

        try {
            if (url != null && properties != null && driver != null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, properties);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection() {

        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        if (connection != null)
            connection.close();
        super.finalize();
    }
}
