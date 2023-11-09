package collections;

import java.util.HashMap;

public class DefaultHashMap<K, V> extends HashMap<K, V> {

  private final DefaultHashMapValueFactory<K, V> valueFactory;

  public DefaultHashMap(DefaultHashMapValueFactory<K, V> valueFactory) {
    this.valueFactory = valueFactory;
  }

  @Override
  public V get(Object key) {
    var value = super.get(key);
    if (value == null) {
      value = valueFactory.createForKey((K) key);
      this.put((K) key, value);
    }
    return value;
  }
}
