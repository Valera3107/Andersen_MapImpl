import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class ArrayBasedMap<K, V> implements Map<K, V> {

  private Pair[] KeyAndValues;

  private int capacity = 16;

  public ArrayBasedMap() {
    KeyAndValues = (Pair[]) Array.newInstance(Pair.class, capacity);
  }

  public ArrayBasedMap(int capacity) {
    this.capacity = capacity;
    KeyAndValues = (Pair[]) Array.newInstance(Pair.class, capacity);
  }

  public ArrayBasedMap(Pair[] keyAndValues) {
    KeyAndValues = keyAndValues;
  }

  public int size() {
    return (int) Arrays.stream(KeyAndValues).filter(Objects::nonNull).count();
  }

  public boolean isEmpty() {
    return Arrays.stream(KeyAndValues).anyMatch(Objects::nonNull);
  }

  public boolean containsKey(Object key) {
    return Arrays.stream(KeyAndValues).filter(Objects::nonNull).anyMatch(e -> e.getKey().equals(key));
  }

  public boolean containsValue(Object value) {
    return Arrays.stream(KeyAndValues).filter(Objects::nonNull).anyMatch(e -> e.getValue().equals(value));
  }

  public V get(Object key) {
    return (V) Arrays.stream(KeyAndValues).filter(e -> e.getKey().equals(key)).findFirst().get();
  }

  private Pair[] copyArray() {
    Pair[] newArray = (Pair[]) Array.newInstance(Pair.class, (int) (capacity * 1.5));
    System.arraycopy(KeyAndValues, 0, newArray, 0, KeyAndValues.length - 1);
    return newArray;
  }

  public V put(K key, V value) {
    V result = value;

    if ((((size() * 100)/capacity)/100) > 0.75) {
      KeyAndValues = copyArray();
    }

    int n = KeyAndValues.length;
    int index = key.hashCode() & (n - 1);
    if (!containsKey(key) & KeyAndValues[index] == null) {
      KeyAndValues[index] = new Pair(key, value);
      return null;
    }
    for (Pair p : KeyAndValues) {
      if (p != null) {
        if (key.equals(p.getKey())) {
          result = p.getValue();
          p.setValue(value);
          break;
        }
      }
    }
    return result;
  }

  public V remove(Object key) {
    V value = null;
    for (int i = 0; i < KeyAndValues.length; i++) {
      final Pair p = KeyAndValues[i];
      if (p.getKey().equals(key)) {
        value = p.getValue();
        KeyAndValues[i] = null;
        return value;
      }
    }
    return value;
  }

  public void putAll(Map<? extends K, ? extends V> m) {
    for (Map.Entry<K, V> e : (Set<Map.Entry<K, V>>) (Set) m.entrySet())
      put(e.getKey(), e.getValue());
  }

  public void clear() {
    Arrays.fill(KeyAndValues, null);
  }

  public Set<K> keySet() {
    return Arrays.stream(KeyAndValues).map(Pair::getKey).collect(Collectors.toSet());
  }

  public Collection<V> values() {
    return Arrays.stream(KeyAndValues).map(Pair::getValue).collect(Collectors.toList());
  }

  public Set<Entry<K, V>> entrySet() {
    return (Set<Entry<K, V>>) (Set) new HashSet<>(Arrays.asList(KeyAndValues));
  }

  private class Pair implements Map.Entry<K, V> {

    private final K key;

    private V value;

    private Pair(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V value) {
      final V oldValue = this.value;
      this.value = value;
      return oldValue;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;

      Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


      if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) return false;
      return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

    }

    @Override
    public int hashCode() {
      return (key == null ? 0 : key.hashCode()) ^
        (value == null ? 0 : value.hashCode());
    }
  }
}
