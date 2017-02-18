package query.mysql;

import static javaslang.API.List;

public class Person extends Table<Person> {
    public final Column.StringC<Person> name = new Column.StringC<>("name", this);
    public final Column.IntC<Person> age = new Column.IntC<>("age", this);

    public final static Person table = new Person();

    public Person() {
        super("person");
    }


    public static void main(String[] args) {

        SelectQuery<Person> s = Person.table
            .select(p -> List(p.name)) // p.name must exist in Person.table
            .where(p -> p.name.eq("Pépe") // name (a String field) can only be compared to string literals, only has operations that make sense on String
                .and(p.age.gt(18)))
            .orderBy(p -> List(p.age.desc()))
            ;

        System.out.println(s.toSQL());
    }

}
