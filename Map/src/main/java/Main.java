import java.util.Objects;

public class Main {
  public static void main(String[] args) {
    ArrayBasedMap<String, Person> map = new ArrayBasedMap<>();
    map.put("Frank", new Person(1, "Frank"));
    map.put("Sam", new Person(2, "Sam"));
    map.put("Bill", new Person(3, "Bill"));
    map.put("Jim", new Person(4, "Jim"));
    map.put("Tim", new Person(5, "Tim"));
    map.put("DDD", new Person(6, "DDD"));
    map.put("SSS", new Person(7, "SSS"));
    map.put("JK", new Person(8, "JK"));
    map.put("BB", new Person(9, "BB"));
    map.put("BB", new Person(9, "FFFFFFFFFFFFF"));

    map.entrySet().stream().filter(Objects::nonNull).forEach(e-> System.out.println(e.getKey() + " " + e.getValue()));
  }
}
