package tppagrupo7.xpress;

import tppagrupo7.xpress.executor.SQLExecutor;
import tppagrupo7.xpress.executor.impl.SQLExecutorImpl;
import tppagrupo7.xpress.generator.SQLGenerator;
import tppagrupo7.xpress.generator.impl.SQLGeneratorImpl;
import tppagrupo7.xpress.util.ConnectionManager;

import java.util.List;

public class XPress {

    private static ConnectionManager manager = new ConnectionManager();
    private static SQLGenerator generator = new SQLGeneratorImpl();
    private static SQLExecutor executor = new SQLExecutorImpl(manager.getConnection());




    public static <T> T find(Class<T> clazz, Object id){
        return executor.executeForSingleRow(generator.selectById(clazz,id));
    }

    public static <T> List<T> findAll(Class<T> clazz){
        return executor.execute(generator.selectAll(clazz));
    }
    /*Metodos por implementar
    public static List<T> query(Class<T> Clazz, String xql, Object arg){
    }
    
    public static <T> T queryForSingleRow(Class<T> Clazz, String xql, Object arg){
    }
    
    public static Transaction beginTransaction(){
    }
    
    public static<T> int insertIfNotExists(Object objt, String arg){
    }
    
    public static<T> int insertIfNotExists(Object objt, String xql, Object arg){
    }
    */
}
