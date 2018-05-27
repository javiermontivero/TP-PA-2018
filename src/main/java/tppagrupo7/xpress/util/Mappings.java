package tppagrupo7.xpress.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import tppagrupo7.xpress.annotation.Column;
import tppagrupo7.xpress.annotation.ManyToOne;
import tppagrupo7.xpress.annotation.OneToMany;
import tppagrupo7.xpress.domain.ColumnMapping;
import tppagrupo7.xpress.domain.Mapping;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

public class Mappings {
    public static <T> List<Mapping<T>> getAllMappings(Class<T> type){
            return Lists.newArrayList(type.getDeclaredFields()).stream().
                    filter(field ->
                            field.getAnnotation(Column.class) != null ||
                            field.getAnnotation(ManyToOne.class) != null ||
                            field.getAnnotation(OneToMany.class) != null).
                    map(field -> {
                        try {
                            if (field.getAnnotation(Column.class) != null) {
                                String columnName = field.getAnnotation(Column.class).name();
                                Method setter = type.getMethod("set" + StringUtils.capitalize(field.getName()),field.getType());
                                return new ColumnMapping<T>(type, field, columnName, setter);
                            }
                            if (field.getAnnotation(ManyToOne.class) != null) {
                                throw new UnsupportedOperationException("@ManyToOne not supported.");//return new ManyToOneMapping<T>(field, type);
                            }
                            if (field.getAnnotation(OneToMany.class) != null) {
                                throw new UnsupportedOperationException("@OneToMany not supported");//return new OneToManyMapping<T>(field, type);
                            }
                        } catch (NoSuchMethodException e){
                            throw new RuntimeException("Can't find setter method for field " + field.getName());
                        }
                        throw new RuntimeException("SHIT HAPPENED.");
                    }).collect(Collectors.toList());
    };
}
