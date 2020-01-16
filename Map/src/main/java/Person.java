import java.util.Objects;

public class Person {
  private long id;
  private String name;

  public Person(long id, String name) {
    this.id = id;
    this.name = name;
  }

  @Override
  public String toString() {
    return "Person{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Person)) return false;
    Person person = (Person) o;
    return id == person.id &&
      Objects.equals(name, person.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
