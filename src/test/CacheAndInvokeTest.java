import annotations.Cache;
import annotations.Invoke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Тестовый класс для проверки совместной работы аннотаций @Cache и @Invoke.
 * Соответствует заданию 2.7: Создайте тест, используя фреймворк JUnit,
 * проверяющий совместную работу аннотаций @Cache и @Invoke на одном классе.
 */
public class CacheAndInvokeTest {

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  /**
   * Тестовый класс с аннотацией @Cache и методом @Invoke.
   * Используется для проверки совместной работы аннотаций.
   */
  @Cache({"users", "orders"})
  public static class TestClassWithCacheAndInvoke {
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
      System.out.println("Метод initializeData() выполнен - данные добавлены в кеш");
    }

    public Map<String, Object> getCache() { return cache; }
    public boolean isMethodExecuted() { return methodExecuted; }
  }

  /**
   * Тестовый класс с пустой аннотацией @Cache.
   * Используется для проверки отключения кеширования.
   */
  @Cache
  public static class TestClassWithEmptyCache {
    private Map<String, Object> cache = new HashMap<>();
    private boolean methodExecuted = false;

    /**
     * Метод с аннотацией @Invoke.
     * Не добавляет данные в кеш, так как @Cache пустой.
     */
    @Invoke
    public void dummyMethod() {
      methodExecuted = true;
      System.out.println("Метод dummyMethod() выполнен - кеширование не активировано");
    }

    public Map<String, Object> getCache() { return cache; }
    public boolean isMethodExecuted() { return methodExecuted; }
  }

  /**
   * Подготовка перед каждым тестом
   */
  @BeforeEach
  void setUp() {
    System.out.println("\n" + "=".repeat(60));
    System.out.println("Начало нового теста");
    System.setOut(new PrintStream(outputStream));
  }

  /**
   * Тест проверяет, что аннотация @Cache корректно возвращает массив строк
   * Соответствует первой части задания 2.7
   */
  @Test
  @DisplayName("Проверка аннотации @Cache с указанными областями")
  void testCacheAnnotationWithAreas() {
    System.out.println("=== ТЕСТ: Проверка аннотации @Cache с указанными областями ===");

    try {
      TestClassWithCacheAndInvoke testObject = new TestClassWithCacheAndInvoke();
      Class<?> clazz = testObject.getClass();

      // Проверка аннотации @Cache
      System.out.println("Проверка наличия аннотации @Cache...");
      boolean hasCacheAnnotation = clazz.isAnnotationPresent(Cache.class);

      if (hasCacheAnnotation) {
        System.out.println("✅ Аннотация @Cache найдена на классе");

        Cache cacheAnnotation = clazz.getAnnotation(Cache.class);
        String[] cacheAreas = cacheAnnotation.value();
        System.out.println("Области кеширования: " + java.util.Arrays.toString(cacheAreas));

        // Проверка корректности значений
        boolean testPassed = true;

        if (cacheAreas.length == 2) {
          System.out.println("✅ Количество областей кеширования корректно: 2");
        } else {
          System.out.println("❌ ОШИБКА: Ожидалось 2 области кеширования, получено: " + cacheAreas.length);
          testPassed = false;
        }

        if (cacheAreas[0].equals("users")) {
          System.out.println("✅ Первая область кеширования корректна: 'users'");
        } else {
          System.out.println("❌ ОШИБКА: Ожидалась область 'users', получено: '" + cacheAreas[0] + "'");
          testPassed = false;
        }

        if (cacheAreas[1].equals("orders")) {
          System.out.println("✅ Вторая область кеширования корректна: 'orders'");
        } else {
          System.out.println("❌ ОШИБКА: Ожидалась область 'orders', получено: '" + cacheAreas[1] + "'");
          testPassed = false;
        }

        // Итоговый результат
        System.out.println("\n" + "=".repeat(40));
        if (testPassed) {
          System.out.println("✅ ТЕСТ ВЫПОЛНЕН УСПЕШНО!");
          System.out.println("Аннотация @Cache корректно возвращает массив строк [\"users\", \"orders\"]");
        } else {
          System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
          fail("Аннотация @Cache возвращает некорректные значения");
        }

        assertTrue(testPassed, "Аннотация @Cache должна возвращать корректные значения");

      } else {
        System.out.println("❌ ОШИБКА: Аннотация @Cache не найдена");
        System.out.println("\n" + "=".repeat(40));
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Аннотация @Cache не найдена");
      }

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста: " + e.getMessage());
      fail("Исключение при выполнении теста: " + e.getMessage());
    }
  }

  /**
   * Тест проверяет, что метод с @Invoke вызывается и выполняется
   * Соответствует второй части задания 2.7
   */
  @Test
  @DisplayName("Проверка вызова и выполнения метода с аннотацией @Invoke")
  void testInvokeMethodExecution() {
    System.out.println("=== ТЕСТ: Проверка вызова и выполнения метода с аннотацией @Invoke ===");

    try {
      TestClassWithCacheAndInvoke testObject = new TestClassWithCacheAndInvoke();
      Class<?> clazz = testObject.getClass();

      // Поиск метода с аннотацией @Invoke
      System.out.println("Поиск метода с аннотацией @Invoke...");
      Method[] methods = clazz.getDeclaredMethods();
      Method invokeMethod = null;

      for (Method method : methods) {
        if (method.isAnnotationPresent(Invoke.class)) {
          invokeMethod = method;
          System.out.println("✅ Найден метод с @Invoke: " + method.getName());
          break;
        }
      }

      if (invokeMethod == null) {
        System.out.println("❌ ОШИБКА: Метод с @Invoke не найден");
        System.out.println("\n" + "=".repeat(40));
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Метод с @Invoke не найден");
      }

      // Автоматический вызов метода с @Invoke через Reflection
      System.out.println("\nАвтоматический вызов метода с @Invoke через Reflection...");
      invokeMethod.setAccessible(true);
      invokeMethod.invoke(testObject);

      // Проверка выполнения метода
      boolean testPassed = true;

      if (testObject.isMethodExecuted()) {
        System.out.println("✅ Метод с @Invoke успешно выполнен");
      } else {
        System.out.println("❌ ОШИБКА: Метод с @Invoke не выполнен");
        testPassed = false;
      }

      // Проверка вывода в консоль
      String output = outputStream.toString();
      if (output.contains("Метод initializeData() выполнен")) {
        System.out.println("✅ Метод вывел сообщение о выполнении");
      } else {
        System.out.println("❌ ОШИБКА: Метод не вывел сообщение о выполнении");
        testPassed = false;
      }

      // Проверка заполнения кеша
      System.out.println("\nПроверка заполнения кеша...");
      Map<String, Object> cache = testObject.getCache();

      if (cache.containsKey("users") && cache.containsKey("orders")) {
        System.out.println("✅ Кеш успешно заполнен данными");
        System.out.println("  - users: " + cache.get("users"));
        System.out.println("  - orders: " + cache.get("orders"));
      } else {
        System.out.println("❌ ОШИБКА: Кеш не заполнен корректно");
        testPassed = false;
      }

      // Итоговый результат
      System.out.println("\n" + "=".repeat(40));
      if (testPassed) {
        System.out.println("✅ ТЕСТ ВЫПОЛНЕН УСПЕШНО!");
        System.out.println("Метод с @Invoke автоматически вызывается и выполняется");
      } else {
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Метод с @Invoke не выполняется корректно");
      }

      assertTrue(testPassed, "Метод с @Invoke должен вызываться и выполняться");

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста: " + e.getMessage());
      fail("Исключение при выполнении теста: " + e.getMessage());
    }
  }

  /**
   * Тест проверяет, что при пустом массиве @Cache кеширование не активируется
   * Соответствует третьей части задания 2.7
   */
  @Test
  @DisplayName("Проверка работы с пустым массивом @Cache")
  void testEmptyCacheAnnotation() {
    System.out.println("=== ТЕСТ: Проверка работы с пустым массивом @Cache ===");

    try {
      TestClassWithEmptyCache testObject = new TestClassWithEmptyCache();
      Class<?> clazz = testObject.getClass();

      // Проверка пустой аннотации @Cache
      System.out.println("Проверка аннотации @Cache с пустым массивом...");
      boolean hasCacheAnnotation = clazz.isAnnotationPresent(Cache.class);

      if (hasCacheAnnotation) {
        Cache cacheAnnotation = clazz.getAnnotation(Cache.class);
        String[] cacheAreas = cacheAnnotation.value();

        boolean testPassed = true;

        if (cacheAreas.length == 0) {
          System.out.println("✅ Массив областей кеширования пуст (как и ожидалось)");
        } else {
          System.out.println("❌ ОШИБКА: Массив @Cache должен быть пустым, но содержит: " +
              java.util.Arrays.toString(cacheAreas));
          testPassed = false;
        }

        // Проверка и вызов метода с @Invoke для класса с пустым кешем
        System.out.println("\nПроверка метода с @Invoke для класса с пустым кешем...");
        Method[] methods = clazz.getDeclaredMethods();
        Method invokeMethod = null;

        for (Method method : methods) {
          if (method.isAnnotationPresent(Invoke.class)) {
            invokeMethod = method;
            System.out.println("✅ Найден метод с @Invoke: " + method.getName());
            break;
          }
        }

        if (invokeMethod != null) {
          invokeMethod.setAccessible(true);
          invokeMethod.invoke(testObject);

          if (testObject.isMethodExecuted()) {
            System.out.println("✅ Метод с @Invoke выполнен");
          } else {
            System.out.println("❌ ОШИБКА: Метод с @Invoke не выполнен");
            testPassed = false;
          }

          // Проверка вывода в консоль
          String output = outputStream.toString();
          if (output.contains("Метод dummyMethod() выполнен")) {
            System.out.println("✅ Метод вывел сообщение о выполнении");
          } else {
            System.out.println("❌ ОШИБКА: Метод не вывел сообщение о выполнении");
            testPassed = false;
          }

          // Проверка, что кеш остался пустым
          Map<String, Object> cache = testObject.getCache();
          if (cache.isEmpty()) {
            System.out.println("✅ Кеширование не активировано (кеш пуст)");
          } else {
            System.out.println("❌ ОШИБКА: Кеширование активировано при пустом @Cache");
            testPassed = false;
          }
        } else {
          System.out.println("❌ ОШИБКА: Метод с @Invoke не найден");
          testPassed = false;
        }

        // Итоговый результат
        System.out.println("\n" + "=".repeat(40));
        if (testPassed) {
          System.out.println("✅ ТЕСТ ВЫПОЛНЕН УСПЕШНО!");
          System.out.println("При пустом массиве @Cache кеширование не активируется");
        } else {
          System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
          fail("При пустом массиве @Cache кеширование не должно активироваться");
        }

        assertTrue(testPassed, "При пустом массиве @Cache кеширование не должно активироваться");

      } else {
        System.out.println("❌ ОШИБКА: Аннотация @Cache не найдена");
        System.out.println("\n" + "=".repeat(40));
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Аннотация @Cache не найдена");
      }

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста: " + e.getMessage());
      fail("Исключение при выполнении теста: " + e.getMessage());
    }
  }

  /**
   * Восстановление оригинального System.out после тестов
   */
  @org.junit.jupiter.api.AfterEach
  void tearDown() {
    System.setOut(originalOut);
    System.out.println(outputStream.toString());
    System.out.println("Конец теста");
    System.out.println("=".repeat(60));
  }
}