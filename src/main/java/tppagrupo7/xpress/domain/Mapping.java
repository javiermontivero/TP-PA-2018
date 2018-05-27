package tppagrupo7.xpress.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;

public abstract class Mapping<T> {
    protected Class<T> type;
    protected Field field;
    protected String columnName;
    protected Method setter;

    public Mapping(Class<T> type, Field field, String columnName, Method setter) {
        this.type = type;
        this.field = field;
        this.columnName = columnName;
        this.setter = setter;
    }

    abstract public T addMappedProperty(T object, ResultSet rs);
}
