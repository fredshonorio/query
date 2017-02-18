package query.mysql.trait;

import query.mysql.Column;
import query.mysql.Table;
import query.mysql.WhereExp;

public interface WhereExpSelf<TABLE extends Table> {
    WhereExp<TABLE> self();
}
