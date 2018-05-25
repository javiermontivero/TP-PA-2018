package tppagrupo7.xpress.executor;

import tppagrupo7.xpress.domain.Statement;

import java.util.List;

public interface SQLExecutor {

    <T> List<T> execute(Statement<T> sentence);
    <T> T executeForSingleRow(Statement<T> sentence);
}
