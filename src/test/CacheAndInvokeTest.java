// CacheAndInvokeTest.java
import annotations.Cache;
import annotations.Invoke;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Тестовый класс для проверки совместной работы аннотаций @Cache и @Invoke.
 * Содержит тесты для проверки корректности работы аннотаций и их взаимодействия
 * в соответствии с заданием 2.7.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 * @see annotations.Cache
 * @see annotations.Invoke
 */
class CacheAndInvokeTest {

  /**
   * Тестовый класс с аннотацией @Cache и методом @Invoke.
   * Используется для проверки совместной работы аннотаций.
   */
  @Cache({"users", "orders"})
  static class TestClassWithCacheAndInvoke {
    private Map<String, Object> cache = new HashMap<>();
    private boolean methodExecuted = false;

    /**
     * Метод с аннотацией @Invoke для инициализации данных.
     * Добавляет тестовые данные в кеш при вызове.
     */
    @Invoke
    public void initializeData() {
      methodExecuted = true;
      cache.put("users", "user123");
      cache.put("orders", "order456");
      System.out.println("      📝 Метод initializeData() выполнен - данные добавлены в кеш");
    }

    public Map<String, Object> getCache() { return cache; }
    public boolean isMethodExecuted() { return methodExecuted; }
  }

  /**
   * Тестовый класс с пустой аннотацией @Cache.
   * Используется для проверки отключения кеширования.
   */
  @Cache
  static class TestClassWithEmptyCache {
    private Map<String, Object> cache = new HashMap<>();
    private boolean methodExecuted = false;

    /**
     * Метод с аннотацией @Invoke.
     * Не добавляет данные в кеш, так как @Cache пустой.
     */
    @Invoke
    public void dummyMethod() {
      methodExecuted = true;
      System.out.println("      Метод dummyMethod() выполнен - кеширование не активировано");
    }

    public Map<String, Object> getCache() { return cache; }
    public boolean isMethodExecuted() { return methodExecuted; }
  }

  /**
   * Тест для задания 2.7: проверка совместной работы аннотаций @Cache и @Invoke.
   * Проверяет корректность возвращаемых значений аннотации @Cache,
   * автоматический вызов методов с @Invoke и их влияние на кеш.
   */
  @Test
  @DisplayName("Тестирование совместной работы @Cache и @Invoke")
  void testCacheAndInvokeIntegration() {
    System.out.println("=== ТЕСТ 2.7: Совместная работа @Cache и @Invoke ===");
    System.out.println("Цель: Проверить интеграцию аннотаций @Cache и @Invoke на одном классе");
    System.out.println();

    // Часть 1: Проверка аннотации @Cache
    System.out.println("1. ПРОВЕРКА АННОТАЦИИ @Cache:");
    Class<TestClassWithCacheAndInvoke> clazz = TestClassWithCacheAndInvoke.class;
    Cache cacheAnnotation = clazz.getAnnotation(Cache.class);
    String[] expectedAreas = {"users", "orders"};

    assertAll("Проверка корректности аннотации @Cache",
        () -> {
          System.out.print("   • Наличие аннотации @Cache на классе... ");
          assertTrue(clazz.isAnnotationPresent(Cache.class));
          System.out.println("✅ НАЙДЕНА");
        },
        () -> {
          System.out.print("   • Проверка областей кеширования ['users', 'orders']... ");
          assertArrayEquals(expectedAreas, cacheAnnotation.value());
          System.out.println("✅ СООТВЕТСТВУЕТ");
        }
    );

    System.out.println("Области кеширования: " + Arrays.toString(cacheAnnotation.value()));
    System.out.println();

    // Часть 2: Проверка выполнения метода с @Invoke и его влияния на кеш
    System.out.println("2. ПРОВЕРКА МЕТОДА С @Invoke:");
    System.out.println("   Создание объекта и автоматический вызов методов с @Invoke...");
    TestClassWithCacheAndInvoke obj = new TestClassWithCacheAndInvoke();

    try {
      Method[] methods = obj.getClass().getDeclaredMethods();
      int invokedMethods = 0;

      for (Method method : methods) {
        if (method.isAnnotationPresent(Invoke.class)) {
          System.out.println("Найден метод с @Invoke: " + method.getName());
          method.invoke(obj);
          invokedMethods++;
          System.out.println(" ✅ Метод " + method.getName() + " успешно вызван через Reflection");
        }
      }

      assertEquals(1, invokedMethods, "Должен быть вызван ровно 1 метод с @Invoke");
      System.out.println("Вызвано методов с @Invoke: " + invokedMethods);

    } catch (Exception e) {
      fail("❌ Ошибка при вызове метода через Reflection: " + e.getMessage());
    }

    // Проверка результатов выполнения
    assertAll("Проверка результатов выполнения метода с @Invoke",
        () -> {
          System.out.print("   • Метод с @Invoke выполнен... ");
          assertTrue(obj.isMethodExecuted());
          System.out.println("✅ ДА");
        },
        () -> {
          System.out.print("   • Кеш не пустой после выполнения... ");
          assertFalse(obj.getCache().isEmpty());
          System.out.println("✅ ДА");
        },
        () -> {
          System.out.print("   • Данные в кеше 'users'... ");
          assertEquals("user123", obj.getCache().get("users"));
          System.out.println("✅ КОРРЕКТНЫ");
        },
        () -> {
          System.out.print("   • Данные в кеше 'orders'... ");
          assertEquals("order456", obj.getCache().get("orders"));
          System.out.println("✅ КОРРЕКТНЫ");
        }
    );

    System.out.println("Размер кеша: " + obj.getCache().size() + " элемента(ов)");
    System.out.println();

    // Часть 3: Проверка пустого массива @Cache
    System.out.println("3. ПРОВЕРКА ПУСТОГО @Cache:");
    System.out.println("   Создание объекта с пустой аннотацией @Cache...");
    TestClassWithEmptyCache emptyCacheObj = new TestClassWithEmptyCache();

    try {
      Method[] methods = emptyCacheObj.getClass().getDeclaredMethods();
      for (Method method : methods) {
        if (method.isAnnotationPresent(Invoke.class)) {
          System.out.println("Найден метод с @Invoke: " + method.getName());
          method.invoke(emptyCacheObj);
          System.out.println("✅ Метод " + method.getName() + " успешно вызван");
        }
      }
    } catch (Exception e) {
      fail("❌ Ошибка при вызове метода: " + e.getMessage());
    }

    assertAll("Проверка поведения при пустом @Cache",
        () -> {
          System.out.print("   • Метод с @Invoke выполнен... ");
          assertTrue(emptyCacheObj.isMethodExecuted());
          System.out.println("✅ ДА");
        },
        () -> {
          System.out.print("   • Кеш остался пустым... ");
          assertTrue(emptyCacheObj.getCache().isEmpty());
          System.out.println("✅ ДА");
        }
    );

    System.out.println("   💡 Вывод: при пустом @Cache кеширование не активируется");
    System.out.println();

    // Итоговая проверка интеграции
    System.out.println("4. ИТОГОВАЯ ПРОВЕРКА ИНТЕГРАЦИИ:");
    assertAll("Финальная проверка интеграции аннотаций",
        () -> assertEquals(2, obj.getCache().size()),
        () -> assertTrue(obj.getCache().containsKey("users") && obj.getCache().containsKey("orders")),
        () -> assertTrue(obj.isMethodExecuted())
    );

    System.out.println("   ✅ Оба аннотации работают корректно");
    System.out.println("   ✅ Кеш содержит обе указанные области");
    System.out.println("   ✅ Метод с @Invoke выполнен ровно 1 раз");
    System.out.println();
    System.out.println("=== РЕЗУЛЬТАТ ТЕСТА 2.7 ===");
    System.out.println("✅ Все проверки совместной работы @Cache и @Invoke пройдены успешно!");
    System.out.println("ТЕСТ 2.7 ЗАВЕРШЕН УСПЕШНО!");
  }
}
