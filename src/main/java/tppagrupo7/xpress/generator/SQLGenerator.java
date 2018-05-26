package tppagrupo7.xpress.generator;

import tppagrupo7.xpress.domain.Query;

public interface SQLGenerator {
    <T> Query<T> selectById(Class<T> clazz, Object id);
    <T> Query<T> selectAll(Class<T> usuarioClass);
}
