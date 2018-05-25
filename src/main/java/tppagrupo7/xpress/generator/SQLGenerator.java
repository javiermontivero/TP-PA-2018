package tppagrupo7.xpress.generator;

import tppagrupo7.xpress.domain.Statement;

public interface SQLGenerator {
    <T> Statement<T> selectById(Class<T> clazz, Object id);
    <T> Statement<T> selectAll(Class<T> usuarioClass);
}
