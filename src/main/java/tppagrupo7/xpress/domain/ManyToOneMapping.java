package tppagrupo7.xpress.domain;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;

public class ManyToOneMapping<T> extends Mapping<T> {
    public ManyToOneMapping(Class<T> type, Field field, String columnName, Method setter) {
        super(type, field, columnName, setter);
    }

    @Override
    public T addMappedProperty(T object, ResultSet rs) {
        return null;
    }
}
