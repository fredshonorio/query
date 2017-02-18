package query.mysql.trait;

import query.mysql.Column;
import query.mysql.Table;

public interface ColumnSelf<TYPE, TABLE extends Table> {
    Column<TYPE, TABLE> self();
}
