package entities;

import annotations.Cache;
import annotations.Invoke;

/**
 * Класс для демонстрации работы аннотации @Cache.
 * Содержит методы с аннотацией @Invoke для автоматического вызова.
 * Имитирует систему кеширования с использованием HashMap.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 * @see annotations.Cache
 * @see annotations.Invoke
 */
@Cache({"users", "orders", "products"})
public class ClassWithCache {

  /** Внутренний кеш для хранения данных */
  private java.util.Map<String, Object> cache = new java.util.HashMap<>();

  /**
   * Метод для инициализации кеша. Вызывается автоматически благодаря @Invoke.
   * Добавляет пустые списки для пользователей и заказов в кеш.
   */
  @Invoke
  public void initializeCache() {
    cache.put("users", new java.util.ArrayList<String>());
    cache.put("orders", new java.util.ArrayList<Integer>());
    System.out.println("Кеш инициализирован методами: users, orders");
  }

  /**
   * Метод для очистки кеша. Вызывается автоматически благодаря @Invoke.
   * Удаляет все данные из внутреннего кеша.
   */
  @Invoke
  public void clearCache() {
    cache.clear();
    System.out.println("Кеш очищен");
  }

  /**
   * Добавляет значение в кеш по указанному ключу.
   *
   * @param key ключ для сохранения значения
   * @param value значение для сохранения в кеше
   */
  public void addToCache(String key, Object value) {
    cache.put(key, value);
  }

  /**
   * Возвращает значение из кеша по указанному ключу.
   *
   * @param key ключ для поиска значения
   * @return значение из кеша или null, если ключ не найден
   */
  public Object getFromCache(String key) {
    return cache.get(key);
  }
}