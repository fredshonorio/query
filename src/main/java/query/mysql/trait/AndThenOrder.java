package query.mysql.trait;

import query.mysql.OrderBy;
import query.mysql.Table;
import query.mysql.WhereExp;

public class AndThenOrder {
    public static interface Desc<TYPE, TABLE extends Table> extends ColumnSelf<TYPE, TABLE> {
        default OrderBy.OrderColumn<TABLE> desc() {
            return new OrderBy.OrderColumn<TABLE>(
                self(),
                OrderBy.Order.DESC
            );
        }
    }
}
