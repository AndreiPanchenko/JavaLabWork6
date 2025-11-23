package entities;

import annotations.Cache;
import annotations.Invoke;

@Cache({"users", "orders", "products"})
public class ClassWithCache {

  private java.util.Map<String, Object> cache = new java.util.HashMap<>();

  @Invoke
  public void initializeCache() {
    cache.put("users", new java.util.ArrayList<String>());
    cache.put("orders", new java.util.ArrayList<Integer>());
    System.out.println("Кеш инициализирован методами: users, orders");
  }

  @Invoke
  public void clearCache() {
    cache.clear();
    System.out.println("Кеш очищен");
  }

  public void addToCache(String key, Object value) {
    cache.put(key, value);
  }

  public Object getFromCache(String key) {
    return cache.get(key);
  }
}