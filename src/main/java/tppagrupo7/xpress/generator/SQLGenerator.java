package tppagrupo7.xpress.generator;

public interface SQLGenerator {
    <T> String selectById(Class<T> clazz, int id);
    <T> String selectAll(Class<T> usuarioClass);
}
