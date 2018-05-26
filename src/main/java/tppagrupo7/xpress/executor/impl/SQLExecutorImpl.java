package tppagrupo7.xpress.executor.impl;

import com.google.common.collect.Lists;
import tppagrupo7.xpress.domain.Query;
import tppagrupo7.xpress.executor.SQLExecutor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SQLExecutorImpl implements SQLExecutor {
    private Connection connection;


    public SQLExecutorImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public <T> List<T> execute(Query<T> sentence) {
        List<T> objects = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(sentence.getQuery());
            Constructor<T> constructor = sentence.getExpectedType().getConstructor();
            while(rs.next()){
                T object = constructor.newInstance();
                buildObject(sentence.getExpectedType().getMethods(), setProperty(sentence, rs, object));
                objects.add(object);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't connect to DB.",e);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Empty constructor not defined.",e);
        }

        return objects;
    }

    @Override
    public <T> T executeForSingleRow(Query<T> sentence) {
        T object = null;
        try(Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sentence.getQuery());rs.next();
            Constructor<T> constructor = sentence.getExpectedType().getConstructor();
            object = constructor.newInstance();
            T finalObject = object;
            buildObject(sentence.getExpectedType().getMethods(), setProperty(sentence, rs, finalObject));
        } catch (IllegalAccessException | InstantiationException | SQLException | NoSuchMethodException | InvocationTargetException e){
            throw new RuntimeException("No empty constructor defined " + sentence.getExpectedType().getSimpleName() + ".");
        }
        return object;
    }

    private void buildObject(Method[] methods, Consumer<Method> methodConsumer) {
        Lists.newArrayList(methods).stream().
                filter(filterSetters()).
                forEach(methodConsumer);
    }

    private <T> Consumer<Method> setProperty(Query<T> sentence, ResultSet rs, T finalObject) {
        return setter -> {
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
        };
    }

    private Predicate<Method> filterSetters() {
        return method -> method.getName().contains("set");
    }
}
