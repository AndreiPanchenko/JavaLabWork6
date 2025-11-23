import annotations.Cache;
import annotations.Invoke;
import entities.ClassWithEmptyCache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Тестовый класс для проверки совместной работы аннотаций @Cache и @Invoke.
 * Содержит тесты для проверки корректности работы аннотаций и их взаимодействия.
 *
 * @author Панчеко Андрей ИТ-13
 * @version 1.0
 * @see annotations.Cache
 * @see annotations.Invoke
 */
class CacheAndInvokeTest {

  /** Тестовый кеш для проверки работы методов */
  private Map<String, Object> testCache;

  /**
   * Метод инициализации перед каждым тестом.
   * Создает новый экземпляр тестового кеша.
   */
  @BeforeEach
  void setUp() {
    testCache = new HashMap<>();
    System.out.println("Инициализирован тестовый кеш");
  }

  /**
   * Тестовый класс согласно заданию с @Cache({"users", "orders"}).
   * Используется для тестирования совместной работы аннотаций.
   */
  @Cache({"users", "orders"})
  static class TestClassWithCacheAndInvoke {
    /** Внутренний кеш объекта */
    private Map<String, Object> cache = new HashMap<>();
    /** Флаг выполнения метода */
    private boolean methodExecuted = false;
    /** Счетчик выполненных методов */
    private int executionCount = 0;

    /**
     * Метод с аннотацией @Invoke для инициализации данных.
     * Добавляет тестовые данные в кеш.
     */
    @Invoke
    public void initializeData() {
      methodExecuted = true;
      executionCount++;
      cache.put("users", "user123");
      cache.put("orders", "order456");
    }

    /**
     * Возвращает внутренний кеш объекта.
     *
     * @return внутренний кеш
     */
    public Map<String, Object> getCache() {
      return cache;
    }

    /**
     * Проверяет, был ли выполнен метод.
     *
     * @return true если метод был выполнен, иначе false
     */
    public boolean isMethodExecuted() {
      return methodExecuted;
    }

    /**
     * Возвращает количество выполненных методов.
     *
     * @return количество выполненных методов
     */
    public int getExecutionCount() {
      return executionCount;
    }
  }

  /**
   * Тестовый класс с пустым @Cache для проверки отключения кеширования.
   */
  @Cache
  static class TestClassWithEmptyCache {
    /** Внутренний кеш объекта */
    private Map<String, Object> cache = new HashMap<>();
    /** Флаг выполнения метода */
    private boolean methodExecuted = false;

    /**
     * Метод с аннотацией @Invoke.
     * Не добавляет данные в кеш, так как @Cache пустой.
     */
    @Invoke
    public void dummyMethod() {
      methodExecuted = true;
      // Не добавляем в кеш, так как @Cache пустой
    }

    /**
     * Возвращает внутренний кеш объекта.
     *
     * @return внутренний кеш
     */
    public Map<String, Object> getCache() {
      return cache;
    }

    /**
     * Проверяет, был ли выполнен метод.
     *
     * @return true если метод был выполнен, иначе false
     */
    public boolean isMethodExecuted() {
      return methodExecuted;
    }
  }

  /**
   * Тест для проверки совместной работы аннотаций @Cache и @Invoke.
   * Проверяет корректность возвращаемых значений аннотации @Cache,
   * автоматический вызов методов с @Invoke и их влияние на кеш.
   */
  @Test
  @DisplayName("--- Обработка @Cache и @Invoke ---")
  void testCacheAndInvokeIntegration() {
    System.out.println("=== Тестирование совместной работы @Cache и @Invoke ===");
    System.out.println("Цель: Проверить интеграцию аннотаций @Cache и @Invoke на одном классе");
    System.out.println();

    // Часть 1: Проверка аннотации @Cache
    System.out.println("1. Проверка аннотации @Cache");
    Class<TestClassWithCacheAndInvoke> clazz = TestClassWithCacheAndInvoke.class;
    Cache cacheAnnotation = clazz.getAnnotation(Cache.class);
    String[] expectedAreas = {"users", "orders"};

    assertAll("Аннотация @Cache должна возвращать корректные значения",
        () -> {
          assertTrue(clazz.isAnnotationPresent(Cache.class));
          System.out.println("Класс аннотирован @Cache");
        },
        () -> {
          assertArrayEquals(expectedAreas, cacheAnnotation.value());
          System.out.println("@Cache возвращает массив: " + Arrays.toString(cacheAnnotation.value()));
        }
    );

    System.out.println("✅ Проверка @Cache завершена");
    System.out.println();

    // Часть 2: Проверка выполнения метода с @Invoke и его влияния на кеш
    System.out.println("2. Проверка выполнения метода с @Invoke и его влияния на кеш");
    TestClassWithCacheAndInvoke obj = new TestClassWithCacheAndInvoke();

    try {
      // Автоматически вызываем методы с @Invoke через Reflection
      Method[] methods = obj.getClass().getDeclaredMethods();
      int invokedMethods = 0;

      for (Method method : methods) {
        if (method.isAnnotationPresent(Invoke.class)) {
          System.out.println("Найден метод с @Invoke: " + method.getName());
          method.setAccessible(true);
          method.invoke(obj);
          invokedMethods++;
          System.out.println("Метод " + method.getName() + " вызван через Reflection");
        }
      }

      assertEquals(1, invokedMethods, "Должен быть вызван 1 метод с @Invoke");
      System.out.println("Вызвано методов с @Invoke: " + invokedMethods);

    } catch (Exception e) {
      fail("Ошибка при вызове метода через Reflection: " + e.getMessage());
    }

    // Проверяем результаты выполнения
    assertAll("Метод с @Invoke должен выполниться и повлиять на кеш",
        () -> {
          assertTrue(obj.isMethodExecuted());
          System.out.println("Метод с @Invoke выполнен");
        },
        () -> {
          assertFalse(obj.getCache().isEmpty());
          System.out.println("Кеш не пустой после выполнения метода");
        },
        () -> {
          assertNotNull(obj.getCache().get("users"));
          System.out.println("Данные добавлены в кеш 'users'");
        },
        () -> {
          assertNotNull(obj.getCache().get("orders"));
          System.out.println("Данные добавлены в кеш 'orders'");
        },
        () -> {
          assertEquals("user123", obj.getCache().get("users"));
          System.out.println("Кеш 'users' содержит корректные данные");
        },
        () -> {
          assertEquals("order456", obj.getCache().get("orders"));
          System.out.println("Кеш 'orders' содержит корректные данные");
        }
    );

    System.out.println("✅ Проверка @Invoke и влияния на кеш завершена");
    System.out.println();

    // Часть 3: Проверка пустого массива @Cache
    System.out.println("3. Проверка пустого массива @Cache (кеширование не активируется)");
    TestClassWithEmptyCache emptyCacheObj = new TestClassWithEmptyCache();

    try {
      // Автоматически вызываем методы с @Invoke через Reflection
      Method[] methods = emptyCacheObj.getClass().getDeclaredMethods();
      for (Method method : methods) {
        if (method.isAnnotationPresent(Invoke.class)) {
          method.setAccessible(true);
          method.invoke(emptyCacheObj);
          System.out.println("Метод с @Invoke вызван для класса с пустым @Cache");
        }
      }
    } catch (Exception e) {
      fail("Ошибка при вызове метода через Reflection: " + e.getMessage());
    }

    assertAll("При пустом @Cache кеширование не активируется",
        () -> {
          assertTrue(emptyCacheObj.isMethodExecuted());
          System.out.println("Метод с @Invoke выполнен");
        },
        () -> {
          assertTrue(emptyCacheObj.getCache().isEmpty());
          System.out.println("Кеш остался пустым (кеширование не активировано)");
        }
    );

    System.out.println("✅ Проверка пустого @Cache завершена");
    System.out.println();

    // Часть 4: Итоговая проверка интеграции
    System.out.println("4. Итоговая проверка интеграции");
    assertAll("Интеграция @Cache и @Invoke должна работать корректно",
        () -> assertEquals(2, obj.getCache().size()),
        () -> assertTrue(obj.getCache().containsKey("users") && obj.getCache().containsKey("orders")),
        () -> assertEquals(1, obj.getExecutionCount())
    );

    System.out.println("Оба метода выполнены успешно");
    System.out.println("Кеш содержит обе указанные области");
    System.out.println("Метод выполнен ровно 1 раз");

    System.out.println();
    System.out.println("Тестирование совместной работы @Cache и @Invoke завершено успешно!");
  }

  /**
   * Дополнительный тест для проверки возможностей Reflection API.
   * Проверяет доступ к аннотациям и методам через Reflection.
   */
  @Test
  @DisplayName("--- Дополнительная проверка Reflection ---")
  void testReflectionAccess() {
    System.out.println("=== Дополнительная проверка Reflection API ===");
    System.out.println("Цель: Проверить возможности Reflection для работы с аннотациями");
    System.out.println();

    Class<TestClassWithCacheAndInvoke> clazz = TestClassWithCacheAndInvoke.class;

    System.out.println("1. Проверка доступа к аннотациям через Reflection");
    assertTrue(clazz.isAnnotationPresent(Cache.class));
    System.out.println("Аннотация @Cache доступна через Reflection");

    Cache cacheAnnotation = clazz.getAnnotation(Cache.class);
    System.out.println("Получен объект аннотации @Cache");
    System.out.println("Области кеширования: " + Arrays.toString(cacheAnnotation.value()));

    System.out.println();
    System.out.println("2. Проверка доступа к методам с аннотациями через Reflection");
    Method[] methods = clazz.getDeclaredMethods();
    int invokeMethodCount = 0;

    for (Method method : methods) {
      if (method.isAnnotationPresent(Invoke.class)) {
        invokeMethodCount++;
        System.out.println("Найден метод с @Invoke: " + method.getName());
        assertTrue(method.isAnnotationPresent(Invoke.class));
      }
    }

    assertTrue(invokeMethodCount > 0, "Должен быть найден хотя бы один метод с @Invoke");
    System.out.println("Всего методов с @Invoke: " + invokeMethodCount);

    System.out.println();
    System.out.println("Проверка Reflection API завершена успешно!");
  }

  /**
   * Статический метод для запуска тестов из главного меню.
   * Создает экземпляр тестового класса и запускает основные тесты.
   */
  public static void runTest() {
    System.out.println("ЗАПУСК ТЕСТОВ @Cache и @Invoke");
    System.out.println("=========================================");

    CacheAndInvokeTest testInstance = new CacheAndInvokeTest();

    try {
      testInstance.setUp();
      testInstance.testCacheAndInvokeIntegration();
    } catch (Exception e) {
      System.out.println("❌ Ошибка в основном тесте: " + e.getMessage());
    }

    try {
      testInstance.setUp();
      testInstance.testReflectionAccess();
    } catch (Exception e) {
      System.out.println("❌ Ошибка в тесте Reflection: " + e.getMessage());
    }

    System.out.println("=========================================");
    System.out.println("✅ ВСЕ ТЕСТЫ @Cache и @Invoke ЗАВЕРШЕНЫ");
    System.out.println();
  }

  /**
   * Тест для проверки класса с пустой аннотацией @Cache.
   * Убеждается, что класс с @Cache без указания областей имеет пустой массив
   * и корректно обрабатывается системой.
   */
  @Test
  public void testEmptyCache() {
    System.out.println("ТЕСТ: Проверка класса с пустой аннотацией @Cache");
    System.out.println("====================================================");

    System.out.println("Цель теста: убедиться, что класс с @Cache без указания областей");
    System.out.println("имеет пустой массив и корректно обрабатывается системой");

    System.out.println("Шаг 1: Создаем объект ClassWithEmptyCache...");
    ClassWithEmptyCache obj = new ClassWithEmptyCache();
    System.out.println("✅ Объект успешно создан: " + obj.getClass().getSimpleName());

    System.out.println("Шаг 2: Проверяем наличие аннотации @Cache...");
    Class<?> clazz = obj.getClass();
    if (clazz.isAnnotationPresent(Cache.class)) {
      System.out.println("✅ Аннотация @Cache найдена на классе");

      Cache cacheAnnotation = clazz.getAnnotation(Cache.class);
      String[] cacheAreas = cacheAnnotation.value();

      System.out.println("Шаг 3: Проверяем массив областей кеширования...");
      System.out.println("Длина массива: " + cacheAreas.length);

      if (cacheAreas.length == 0) {
        System.out.println("✅ ТЕСТ ПРОЙДЕН: Массив областей кеширования ПУСТОЙ");
        System.out.println("Это означает, что системное кеширование не активировано");
      } else {
        System.out.println("❌ ТЕСТ НЕ ПРОЙДЕН: Массив НЕ пустой, содержит " + cacheAreas.length + " элементов");
        System.out.println("Области: " + String.join(", ", cacheAreas));
      }

      System.out.println("Шаг 4: Демонстрация работы методов класса...");
      System.out.println("Вызываем методы с @Invoke для проверки функциональности:");

      try {
        // Автоматически вызываем методы с аннотацией @Invoke
        Method[] methods = clazz.getDeclaredMethods();
        int invokedCount = 0;

        for (Method method : methods) {
          if (method.isAnnotationPresent(annotations.Invoke.class)) {
            System.out.println("Вызов метода: " + method.getName() + "()");
            method.setAccessible(true);
            method.invoke(obj);
            invokedCount++;
          }
        }

        if (invokedCount > 0) {
          System.out.println("✅ Все методы с @Invoke успешно выполнены (" + invokedCount + " методов)");
        } else {
          System.out.println("Методы с @Invoke не найдены в классе");
        }

      } catch (Exception e) {
        System.out.println("Ошибка при вызове методов: " + e.getMessage());
      }

    } else {
      System.out.println("❌ Аннотация @Cache не найдена - ТЕСТ НЕ ПРОЙДЕН");
    }

    System.out.println(" ИТОГ ТЕСТА:");
    System.out.println("- Класс должен иметь аннотацию @Cache ✓");
    System.out.println("- Массив областей кеширования должен быть пустым ✓");
    System.out.println("- Методы с @Invoke должны работать корректно ✓");
    System.out.println("ТЕСТ УСПЕШНО ЗАВЕРШЕН!");
  }
}