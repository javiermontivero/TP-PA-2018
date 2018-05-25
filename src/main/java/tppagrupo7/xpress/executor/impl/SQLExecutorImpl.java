package tppagrupo7.xpress.executor.impl;

import com.google.common.collect.Lists;
import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.config.ConfigLoader;
import tppagrupo7.xpress.config.Configuration;
import tppagrupo7.xpress.domain.Statement;
import tppagrupo7.xpress.executor.SQLExecutor;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class SQLExecutorImpl implements SQLExecutor {
    private Connection connection;
    private ConfigLoader configLoader;

    @Override
    public <T> List<T> execute(Statement<T> sentence) {
        return null;
    }

    @Override
    public <T> T executeForSingleRow(Statement<T> sentence) {
        Configuration config = configLoader.getConfiguration();
        try {
            Class<?> driverClass = Class.forName(config.getDriver());
            Driver driver = (Driver) driverClass.newInstance();
            Properties properties = new Properties();
            properties.setProperty("user",config.getUser());
            properties.setProperty("password",config.getPassword());
            connection = driver.connect(config.getUrl(),properties);

            ResultSet rs = connection.createStatement().executeQuery(sentence.getQuery());

            try{
                Constructor<T> constructor = sentence.getExpectedType().getConstructor();
                T object = constructor.newInstance();
                Lists.newArrayList(sentence.getExpectedType().getMethods()).stream().
                        filter(method -> method.getName().contains("set")).
                        forEach(setter -> {
                            String fieldName = setter.getName().split("set")[1];
                            try{
                                Field field = sentence.getExpectedType().getField(fieldName);
                                setter.invoke(object,rs.getObject(fieldName,field.getType()));
                            }catch (NoSuchFieldException e){
                                throw new RuntimeException("No field " + fieldName + " defined.",e);
                            } catch (SQLException e){
                                throw new RuntimeException("No column named " + fieldName + " defined.",e);
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        });
            } catch (NoSuchMethodException | InvocationTargetException e){
                throw new RuntimeException("No empty constructor defined.");
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
