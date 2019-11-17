package com.minsk.BSU.abliznets.cafe.connection;

import com.minsk.BSU.abliznets.cafe.property.PropertyReader;
import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DATABASE_PROPERTIES_PATH = "database.properties";
    private static final String URL_PROPERTY = "url";
    private static final String USER_PROPERTY = "user";
    private static final String PASSWORD_PROPERTY = "password";

    public Connection create() throws SQLException {
        Driver driver = new Driver();
        DriverManager.registerDriver(driver);

        PropertyReader propertyReader = new PropertyReader(DATABASE_PROPERTIES_PATH);
        String url = propertyReader.read(URL_PROPERTY);
        String user = propertyReader.read(USER_PROPERTY);
        String password = propertyReader.read(PASSWORD_PROPERTY);

        return DriverManager.getConnection(url, user, password);
    }
}
