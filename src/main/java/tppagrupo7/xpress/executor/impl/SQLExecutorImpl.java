package tppagrupo7.xpress.executor.impl;

import com.google.common.collect.Lists;
import tppagrupo7.xpress.domain.Query;
import tppagrupo7.xpress.executor.SQLExecutor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLExecutorImpl implements SQLExecutor {
    private Connection connection;


    public SQLExecutorImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public <T> List<T> execute(Query<T> sentence) {
        return null;
    }

    @Override
    public <T> T executeForSingleRow(Query<T> sentence) {
        T object = null;
        try(java.sql.Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sentence.getQuery());rs.next();
            Constructor<T> constructor = sentence.getExpectedType().getConstructor();
            object = constructor.newInstance();
            T finalObject = object;
            Lists.newArrayList(sentence.getExpectedType().getMethods()).stream().
                    filter(method -> method.getName().contains("set")).
                    forEach(setter -> {
                        String fieldName = setter.getName().split("set")[1].toLowerCase();
                        try {
                            Field field = sentence.getExpectedType().getDeclaredField(fieldName);
                            setter.invoke(finalObject, rs.getObject(fieldName, field.getType()));
                        } catch (NoSuchFieldException e) {
                            throw new RuntimeException("No field " + fieldName + " defined.", e);
                        } catch (SQLException e) {
                            throw new RuntimeException("No column named " + fieldName + " defined.", e);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            throw new RuntimeException("No appropiate setter defined for field " + fieldName + ".", e);
                        }
                    });
        } catch (IllegalAccessException | InstantiationException | SQLException | NoSuchMethodException | InvocationTargetException e){
            throw new RuntimeException("No empty constructor defined " + sentence.getExpectedType().getSimpleName() + ".");
        }
        return object;
    }
}
