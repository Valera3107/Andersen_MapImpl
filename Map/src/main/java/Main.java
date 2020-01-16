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

    map.put("B984u 98cqu498B", new Person(64356, "y734y7n4y7nf7"));
    map.put("ktngosjtbnosbostnbsotkbso",new Person(222, "wkiejrgnweijrgwoe"));

    System.out.println("Remove: " + map.remove("DDD"));

    System.out.println("Size: " + map.size());

    System.out.println("Contains key: " + map.containsKey("Sam"));

    System.out.println("Contains value: " + map.containsValue(new Person(1, "Frank")));


    map.entrySet().stream().filter(Objects::nonNull).forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
  }
}
