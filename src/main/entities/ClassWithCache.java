package entities;

import annotations.Cache;
import annotations.Invoke;

/**
 * Класс для демонстрации работы аннотации @Cache.
 * Соответствует заданию 1.6: Проаннотируйте класс аннотацией @Cache,
 * указав несколько кешируемых областей.
 */
@Cache({"users", "orders", "products"})
public class ClassWithCache {
  /** Внутренний кеш для хранения данных */
  private java.util.Map<String, Object> cache = new java.util.HashMap<>();

  /**
   * Метод для инициализации кеша. Вызывается автоматически благодаря @Invoke.
   * Соответствует заданию 2.7: метод, помеченный @Invoke для автоматического вызова.
   */
  @Invoke
  public void initializeCache() {
    cache.put("users", new java.util.ArrayList<String>());
    cache.put("orders", new java.util.ArrayList<Integer>());
    System.out.println("Кеш инициализирован");
  }
}