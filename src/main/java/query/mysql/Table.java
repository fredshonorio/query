package query.mysql;

import javaslang.collection.List;

import java.util.function.Function;

public abstract class Table<T extends Table> {

    public final String tableName;
    public Table(String tableName) {
        this.tableName = tableName;
    }

    public SelectQuery<T> select(Function<T, List<Column<?, T>>> t) {
        return new SelectQuery<T>(
            (T) this, // why? why not?
            t.apply((T) this)
        );
    }
}
