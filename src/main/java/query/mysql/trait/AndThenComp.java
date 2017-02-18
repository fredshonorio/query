package query.mysql.trait;

import query.mysql.Table;
import query.mysql.WhereExp;

// objects that implement these interfaces get a fluent method for sql comparison operations, eg:
// if X implements Eq then x.eq(...) is possible
public class AndThenComp {
    public static interface Eq<TYPE, TABLE extends Table> extends ColumnSelf<TYPE, TABLE> {
        default WhereExp.Eq<TYPE, TABLE> eq(TYPE t) {
            return new WhereExp.Eq<>(self(), t);
        }
    }

    public static interface Gt<TYPE extends Number, TABLE extends Table> extends ColumnSelf<TYPE, TABLE> {
        default WhereExp.Gt<TYPE, TABLE> gt(TYPE t) {
            return new WhereExp.Gt<>(self(), t);
        }
    }

    public interface AllIdentity<TYPE, TABLE extends Table> extends
        Eq<TYPE, TABLE> // neq
    {}

    public interface AllNumeric<TYPE extends Number, TABLE extends Table> extends
        Eq<TYPE, TABLE>,
        Gt<TYPE, TABLE> // lt, lte
    {}
}
