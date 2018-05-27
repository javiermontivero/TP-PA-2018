package tppagrupo7.xpress.executor;

import tppagrupo7.xpress.domain.Query;

import java.util.List;
import java.util.Optional;

public interface SQLExecutor {

    <T> List<T> execute(Query<T> sentence);
    <T> Optional<T> executeForSingleRow(Query<T> sentence);
}
