package query.mysql.trait;

import javaslang.collection.List;
import query.mysql.Table;
import query.mysql.WhereExp;


// objects that implement these interfaces get a fluent method for sql boolean operations, eg:
// if X implements And then x.and(...) is possible
public abstract class AndThenBool {
    private AndThenBool() {}

    // is associativity correct?
    // https://dev.mysql.com/doc/refman/5.7/en/operator-precedence.html
    interface And<TABLE extends Table> extends WhereExpSelf<TABLE> {
        default WhereExp.And<TABLE> and(WhereExp<TABLE> right) {
            return new WhereExp.And<TABLE>(
                List.of(self(), right)
            );
        }
    }

    public interface All<TABLE extends Table> extends
        And<TABLE> // or, not
    {};
}
