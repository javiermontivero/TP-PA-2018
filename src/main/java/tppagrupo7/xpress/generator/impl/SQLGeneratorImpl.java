package tppagrupo7.xpress.generator.impl;

import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.Table;
import tppagrupo7.xpress.generator.SQLGenerator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class SQLGeneratorImpl implements SQLGenerator {
    private String select = "SELECT * FROM ";
    private String where = " WHERE ";

    public <T> String selectById(Class<T> clazz, int id) {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        Optional<Field> idFieldName = fields.stream().filter(field -> field.isAnnotationPresent(Id.class)).findFirst();
        if(!clazz.isAnnotationPresent(Table.class)){
            throw new RuntimeException("Cannot find id table name in class " + clazz.getSimpleName() + ".");
        }
        if(idFieldName.isPresent())
            return select + clazz.getSimpleName() + where + idFieldName.get().getName() + " = " + id + ";";
        else{
            throw new RuntimeException("Cannot find id field in class " + clazz.getSimpleName() + ".");
        }
    }

    @Override
    public <T> String selectAll(Class<T> clazz) {
        String tabla = clazz.getAnnotation(Table.class).name();
        return select + tabla + ";";
    }

}
