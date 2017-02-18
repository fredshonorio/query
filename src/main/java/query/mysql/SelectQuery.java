package query.mysql;

import javaslang.collection.List;
import javaslang.control.Option;

import java.util.function.Function;

public class SelectQuery<Tab extends Table> implements ToQuery {

    private final Tab from;
    private final List<Column<?, Tab>> projection;
    private Option<WhereExp<Tab>> where;
    private Option<OrderBy<Tab>> orderBy;

    public SelectQuery(Tab from, List<Column<?, Tab>> projection) {
        this.from = from;
        this.projection = projection;
    }

    public SelectQuery<Tab> where(Function<Tab, WhereExp<Tab>> whereExp) {
        where = Option.of(whereExp.apply(from));
        return this;
    }

    public SelectQuery<Tab> orderBy(Function<Tab, List<OrderBy.OrderColumn<Tab>>> orderByF) {
        orderBy = Option.some(new OrderBy<Tab>(orderByF.apply(from)));
        return this;
    }

    @Override
    public String toSQL() {
        return "SELECT " + projection.map(Column::toSQL).mkString(", ") + " " +
            "FROM " + from.tableName
            + where.map(w -> " WHERE " + w.toSQL()).getOrElse("")
            + orderBy.map(o -> " ORDER BY " + o.toSQL()).getOrElse("")
            + ";";
    }
}
