package query.mysql;

@Deprecated // this is just a way to debug, would be removed (or at least allow parameterized queries) eventually
public interface ToQuery {
    public String toSQL();
}
