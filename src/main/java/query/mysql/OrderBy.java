package query.mysql;

import javaslang.collection.List;

public class OrderBy<TABLE extends Table> implements ToQuery {

    public static enum Order {
        ASC, DESC
    }

    public static class OrderColumn<TABLE extends Table> implements ToQuery {
        public final Column<?, TABLE> column;
        public final Order order;


        public OrderColumn(Column<?, TABLE> column, Order order) {
            this.column = column;
            this.order = order;
        }

        @Override
        public String toSQL() {
            return column.toSQL() + " " + order.name();
        }
    }

    public final List<OrderColumn<TABLE>> order;

    public OrderBy(List<OrderColumn<TABLE>> order) {
        this.order = order;
    }

    @Override
    public String toSQL() {
        return order
            .map(OrderColumn::toSQL)
            .mkString(", ");
    }
}
