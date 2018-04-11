package tppagrupo7.xpress;

import tppagrupo7.xpress.executor.SQLExecutor;
import tppagrupo7.xpress.generator.SQLGenerator;

public class XPress {

    private static SQLGenerator generator;
    private static SQLExecutor executor;

    public static <T> T find(Class<T> clazz, int id){

        return executor.execute(generator.selectById(clazz,id));
    }
}
