package query.mysql;

import javaslang.collection.List;
import query.mysql.trait.AndThenBool;

public abstract class WhereExp<TABLE extends Table> implements ToQuery {
    private WhereExp() {
    }

    //
    // boolean
    //

    public abstract static class Boolean<TABLE extends Table> extends WhereExp<TABLE>
        implements AndThenBool.All<TABLE> {
        public final List<WhereExp<TABLE>> exps;

        Boolean(List<WhereExp<TABLE>> exps) {
            this.exps = exps;
        }

        @Override
        public WhereExp<TABLE> self() {
            return this;
        }
    }

    public static class And<TABLE extends Table> extends Boolean<TABLE> {
        public And(List<WhereExp<TABLE>> exps) {
            super(exps);
        }

        @Override
        public String toSQL() {
            return exps.map(ToQuery::toSQL).mkString("(", " AND ", ")");
        }
    }

    //
    //  comparison
    //

    public abstract static class Comparison<TYPE, TABLE extends Table> extends WhereExp<TABLE>
        implements AndThenBool.All<TABLE> {
        public final Column<TYPE, TABLE> column;
        public final TYPE value;

        Comparison(Column<TYPE, TABLE> column, TYPE value) {
            this.column = column;
            this.value = value;
        }

        @Override
        public WhereExp<TABLE> self() {
            return this;
        }
    }

    public static class Eq<TYPE, TABLE extends Table> extends Comparison<TYPE, TABLE> {
        public Eq(Column<TYPE, TABLE> column, TYPE value) {
            super(column, value);
        }

        @Override
        public String toSQL() {
            return column.toSQL() + " = " + value.toString();
        }
    }

    public static class Gt<TYPE extends Number, TABLE extends Table> extends Comparison<TYPE, TABLE> {
        public Gt(Column<TYPE, TABLE> column, TYPE value) {
            super(column, value);
        }

        @Override
        public String toSQL() {
            return column.toSQL() + " > " + value.toString();
        }
    }
}
