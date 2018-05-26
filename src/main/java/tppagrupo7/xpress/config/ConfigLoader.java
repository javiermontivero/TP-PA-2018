package tppagrupo7.xpress.config;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ConfigLoader {

    private Configuration config = null;

    public Configuration getConfiguration() {
        if(config == null)
            return getNewConfiguration();
        else
            return config;
    }

    private Configuration getNewConfiguration(){
        try {
            Properties props = new Properties();
            URL resource = this.getClass().getClassLoader().getResource("jdbc.properties");
            if(resource == null)
                throw new IOException("Can't find jdbc.properties file.");
            props.load(new FileReader(resource.getPath()));

            String driver = props.getProperty("jdbc.connection.driver");
            String url = props.getProperty("jdbc.connection.url");
            String user = props.getProperty("jdbc.connection.user");
            String password = props.getProperty("jdbc.connection.password");
            if(driver == null)
                throw new RuntimeException("Can't find driver property.");
            if(url == null)
                throw new RuntimeException("Can't find url property.");
            if(user == null)
                throw new RuntimeException("Can't find user property.");
            if(password == null)
                throw new RuntimeException("Can't find password property.");
            return new Configuration(url,driver,user,password);
        } catch (IOException e) {
            throw new RuntimeException("Can't find jdbc.properties file.");
        }
    }
}
