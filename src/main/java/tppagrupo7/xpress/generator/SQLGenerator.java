package tppagrupo7.xpress.generator;

public interface SQLGenerator {
    <T> String selectById(Class<T> clazz,int id);
}
