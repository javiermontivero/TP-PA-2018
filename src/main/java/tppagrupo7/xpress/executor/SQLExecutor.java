package tppagrupo7.xpress.executor;

import tppagrupo7.xpress.domain.Query;

import java.util.List;

public interface SQLExecutor {

    <T> List<T> execute(Query<T> sentence);
    <T> T executeForSingleRow(Query<T> sentence);
}
