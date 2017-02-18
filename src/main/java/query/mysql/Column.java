package query.mysql;

import query.mysql.trait.AndThenComp;

public class Column<TYPE, TABLE extends Table> implements ToQuery,
    AndThenComp.AllIdentity<TYPE, TABLE> {

    private final String name;
    private final Class<TYPE> clz;
    private final TABLE table;

    private Column(String name, Class<TYPE> clz, TABLE table) {
        this.name = name;
        this.clz = clz;
        this.table = table;
    }

    @Override
    public String toSQL() {
        return name;
    }

    @Override
    public Column<TYPE, TABLE> self() {
        return this;
    }

    public static class StringC<TABLE extends Table> extends Column<String, TABLE> {
        public StringC(String name, TABLE table) {
            super(name, String.class, table);
        }
    }

    public static class LongC<TABLE extends Table> extends Column<Long, TABLE>
        implements AndThenComp.AllNumeric<Long, TABLE>
    {
        public LongC(String name, TABLE table) {
            super(name, Long.class, table);
        }
    }

    public static class IntC<TABLE extends Table> extends Column<Integer, TABLE>
        implements AndThenComp.AllNumeric<Integer, TABLE>
    {
        public IntC(String name, TABLE table) {
            super(name, Integer.class, table);
        }
    }
}
