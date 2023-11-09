package collections;

public interface DefaultHashMapValueFactory<K, V> {
  V createForKey(K key);
}
