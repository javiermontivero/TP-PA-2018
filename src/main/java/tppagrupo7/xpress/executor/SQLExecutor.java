package tppagrupo7.xpress.executor;

public interface SQLExecutor {

    <T> T execute(String sentence);
}
