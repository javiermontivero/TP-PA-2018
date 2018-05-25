package tppagrupo7.xpress.generator.impl;

import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.Table;
import tppagrupo7.xpress.domain.Statement;
import tppagrupo7.xpress.generator.SQLGenerator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SQLGeneratorImpl implements SQLGenerator {
    private String select = "SELECT * FROM ";
    private String where = " WHERE ";

    public <T> Statement<T> selectById(Class<T> clazz, Object id) {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        Optional<Field> idFieldName = fields.stream().filter(field -> field.isAnnotationPresent(Id.class)).findFirst();
        if(!clazz.isAnnotationPresent(Table.class)){
            throw new RuntimeException("Cannot find id table name in class " + clazz.getSimpleName() + ".");
        }
        if(idFieldName.isPresent()) {
            Statement<T> statement = new Statement<T>();
            statement.setQuery((select + clazz.getSimpleName() + where + idFieldName.get().getName() + " = " + id.toString() + ";").toUpperCase());
            statement.setExpectedType(clazz);
            return statement;
        }
        else{
            throw new RuntimeException("Cannot find id field in class " + clazz.getSimpleName() + ".");
        }
    }

    @Override
    public <T> Statement<T> selectAll(Class<T> clazz) {
        String tabla = clazz.getAnnotation(Table.class).name();
        Statement<T> statement = new Statement<T>();
        statement.setExpectedType(clazz);
        statement.setQuery((select + tabla + ";").toUpperCase());
        return statement;
    }
}
