package tppagrupo7.xpress.util;

import tppagrupo7.xpress.config.ConfigLoader;
import tppagrupo7.xpress.config.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private ConfigLoader configLoader = new ConfigLoader();

    public Connection getConnection(){
        Configuration config = configLoader.getConfiguration();
        Class<?> driverClass = null;
        Driver driver = null;
        try {
            driverClass = Class.forName(config.getDriver());
            driver = (Driver) driverClass.newInstance();
            Properties properties = new Properties();
            properties.setProperty("user",config.getUser());
            properties.setProperty("password",config.getPassword());
            return driver.connect(config.getUrl(),properties);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException  e) {
            throw new RuntimeException("Can't find driver class.");
        }
    }
}
