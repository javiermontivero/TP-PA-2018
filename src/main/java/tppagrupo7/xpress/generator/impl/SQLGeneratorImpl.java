package tppagrupo7.xpress.generator.impl;

import com.google.common.collect.Lists;
import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.Id;
import tppagrupo7.xpress.annotation.ManyToOne;
import tppagrupo7.xpress.annotation.Table;
import tppagrupo7.xpress.domain.Query;
import tppagrupo7.xpress.generator.SQLGenerator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SQLGeneratorImpl implements SQLGenerator {
    private String select = "SELECT * FROM ";
    private String where = " WHERE ";

    public <T> Query<T> selectById(Class<T> clazz, Object id) {
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());

        Optional<Field> idFieldName = fields.stream().filter(field -> field.isAnnotationPresent(Id.class)).findFirst();
        if(!clazz.isAnnotationPresent(Table.class)){
            throw new RuntimeException("Cannot find id table name in class " + clazz.getSimpleName() + ".");
        }
        else {
            char tableAlias = clazz.getAnnotation(Table.class).name().charAt(0);
            List<String> joins = fields.stream().
                    filter(field -> field.isAnnotationPresent(ManyToOne.class)).
                    map(field -> {
                        Class<?> fieldType = field.getType();

                        String joinTableName = fieldType.getAnnotation(Table.class).name();
                        char joinTableAlias = joinTableName.charAt(0);
                        Optional<Field> idField = Lists.newArrayList(fieldType.getDeclaredFields()).stream().filter(thisField -> thisField.isAnnotationPresent(Id.class)).findFirst();
                        if(idField.isPresent())
                            return "INNER JOIN " + joinTableName + " " + joinTableAlias + " ON " + joinTableAlias + "." + idField.get().getAnnotation(Column.class).name() + " = " + tableAlias + "." + field.getAnnotation(ManyToOne.class).columnName();
                        else
                            throw new RuntimeException("Cant find id column.");
                    } ).collect(Collectors.toList());
            if (idFieldName.isPresent()) {
                Query<T> query = new Query<>();
                query.setQuery((
                        select + clazz.getAnnotation(Table.class).name() + " " + tableAlias +
                        joins.stream().reduce("", (join1, join2) -> join1 + " " + join2) +
                        where + idFieldName.get().getName() + " = " + id.toString() + ";").toUpperCase());
                query.setExpectedType(clazz);
                return query;
            } else {
                throw new RuntimeException("Cannot find id field in class " + clazz.getSimpleName() + ".");
            }
        }
    }



    @Override
    public <T> Query<T> selectAll(Class<T> clazz) {
        String tabla = clazz.getAnnotation(Table.class).name();
        Query<T> query = new Query<T>();
        query.setExpectedType(clazz);
        query.setQuery((select + tabla + ";").toUpperCase());
        return query;
    }
}
