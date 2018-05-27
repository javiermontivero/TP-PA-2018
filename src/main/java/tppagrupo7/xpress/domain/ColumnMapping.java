package tppagrupo7.xpress.domain;

import com.google.common.collect.Lists;
import tppagrupo7.xpress.annotation.Column;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class ColumnMapping<T> extends Mapping<T> {

    public ColumnMapping(Class<T> type, Field field, String columnName, Method setter) {
        super(type, field, columnName, setter);
    }

    @Override
    public T addMappedProperty(T object, ResultSet rs) {
        try {
            setter.invoke(object,rs.getObject(columnName,field.getType()));
            return object;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Setter method for field " + field.getName() + " was not defined properly.", e);
        } catch (SQLException e) {
            throw new RuntimeException("Can't find column " + columnName, e);
        }
    }
}
