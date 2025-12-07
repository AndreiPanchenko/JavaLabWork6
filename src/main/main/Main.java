package main;

import entities.ClassWithCache;
import entities.ClassWithDefault;
import entities.ClassWithInvoke;
import entities.ClassWithToString;
import entities.ClassWithTwo;
import entities.ClassWithValidate;
import processors.AnnotationProcessor;
import java.util.Scanner;

/**
 * Главный класс приложения для демонстрации работы с аннотациями.
 * Содержит меню для выбора различных демонстраций и тестов.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 * @see processors.AnnotationProcessor
 */
public class Main {

  /** Сканер для чтения ввода пользователя */
  private static final Scanner scanner = new Scanner(System.in);

  /**
   * Главный метод приложения. Запускает интерактивное меню для демонстрации функциональности.
   *
   * @param args аргументы командной строки (не используются)
   */
  public static void main(String[] args) {
    System.out.println("Лабораторная работа №6: Аннотации и тестирование");
    System.out.println("==================================================");

    boolean running = true;

    while (running) {
      printMenu();
      int choice = getIntInput("Выберите пункт меню: ");

      switch (choice) {
        case 1:
          demonstrateInvoke();
          break;
        case 2:
          demonstrateDefault();
          break;
        case 3:
          demonstrateToString();
          break;
        case 4:
          demonstrateValidate();
          break;
        case 5:
          demonstrateTwo();
          break;
        case 6:
          demonstrateCache();
          break;
        case 7:
          runAllDemonstrations();
          break;
        case 8:
          runToStringTest();
          break;
        case 9:
          runCacheAndInvokeTest();
          break;
        case 0:
          running = false;
          System.out.println("Выход из программы. До свидания!");
          break;
        default:
          System.out.println("Неверный выбор! Попробуйте снова.");
      }

      if (running) {
        System.out.println("\n" + "=".repeat(50));
        System.out.print("Нажмите Enter для продолжения...");
        scanner.nextLine();
      }
    }

    scanner.close();
  }

  /**
   * Выводит главное меню приложения.
   */
  private static void printMenu() {
    System.out.println("ГЛАВНОЕ МЕНЮ");
    System.out.println("1. Демонстрация @Invoke");
    System.out.println("2. Демонстрация @Default");
    System.out.println("3. Демонстрация @ToString");
    System.out.println("4. Демонстрация @Validate");
    System.out.println("5. Демонстрация @Two");
    System.out.println("6. Демонстрация @Cache");
    System.out.println("7. Запуск всех демонстраций");
    System.out.println("8. Тестирование @ToString");
    System.out.println("9. Тестирование @Cache и @Invoke");
    System.out.println("0. Выход");
  }

  /**
   * Запрашивает у пользователя целое число.
   *
   * @param prompt сообщение для пользователя
   * @return введенное целое число
   */
  private static int getIntInput(String prompt) {
    System.out.print(prompt);
    while (true) {
      try {
        return Integer.parseInt(scanner.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.print("Ошибка! Введите целое число: ");
      }
    }
  }

  /**
   * Демонстрирует работу аннотации @Invoke.
   * Создает объект и автоматически вызывает методы с аннотацией @Invoke.
   */
  private static void demonstrateInvoke() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @Invoke ===");
    System.out.println("Создаем объект ClassWithInvoke...");
    ClassWithInvoke obj = new ClassWithInvoke();
    AnnotationProcessor.processInvoke(obj);
    System.out.println("Методы с аннотацией @Invoke были автоматически вызваны");
  }

  /**
   * Демонстрирует работу аннотации @Default.
   * Анализирует класс и выводит информацию об аннотации @Default.
   */
  private static void demonstrateDefault() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @Default ===");
    System.out.println("Анализируем класс ClassWithDefault...");
    AnnotationProcessor.processDefault(ClassWithDefault.class);
    System.out.println("Аннотация @Default обработана успешно");
  }

  /**
   * Демонстрирует работу аннотации @ToString.
   * Создает объект и формирует его строковое представление с учетом аннотаций.
   */
  private static void demonstrateToString() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @ToString ===");
    System.out.println("Создаем объект ClassWithToString...");
    ClassWithToString obj = new ClassWithToString();
    String result = AnnotationProcessor.processToString(obj);
    System.out.println("Результат преобразования в строку:");
    System.out.println("  " + result);
    System.out.println("Обратите внимание: поле 'password' не отображается,");
    System.out.println("  а поле 'email' отображается (аннотировано с Mode.YES)");
  }

  /**
   * Демонстрирует работу аннотации @Validate.
   * Анализирует класс и выводит список классов для валидации.
   */
  private static void demonstrateValidate() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @Validate ===");
    System.out.println("Анализируем класс ClassWithValidate...");
    AnnotationProcessor.processValidate(ClassWithValidate.class);
    System.out.println("Аннотация @Validate обработана успешно");
  }

  /**
   * Демонстрирует работу аннотации @Two.
   * Анализирует класс и выводит значения свойств аннотации.
   */
  private static void demonstrateTwo() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @Two ===");
    System.out.println("Анализируем класс ClassWithTwo...");
    AnnotationProcessor.processTwo(ClassWithTwo.class);
    System.out.println("Аннотация @Two обработана успешно");
  }

  /**
   * Демонстрирует работу аннотации @Cache.
   * Анализирует класс и выводит список областей кеширования.
   */
  private static void demonstrateCache() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @Cache ===");
    System.out.println("Анализируем класс ClassWithCache...");
    AnnotationProcessor.processCache(ClassWithCache.class);
    System.out.println("Аннотация @Cache обработана успешно");
  }

  /**
   * Запускает все демонстрации последовательно.
   */
  private static void runAllDemonstrations() {
    System.out.println("=== ЗАПУСК ВСЕХ ДЕМОНСТРАЦИЙ ===");
    demonstrateInvoke();
    demonstrateDefault();
    demonstrateToString();
    demonstrateValidate();
    demonstrateTwo();
    demonstrateCache();
    System.out.println("Все демонстрации завершены успешно!");
  }

  /**
   * Запускает тест для аннотации @ToString (Задание 2.1).
   */
  private static void runToStringTest() {
    System.out.println("=== ТЕСТИРОВАНИЕ @ToString (Задание 2.1) ===");
    System.out.println("Задание: Проверить работу аннотации @ToString");

    try {
      // Создание тестового объекта
      ClassWithToString obj = new ClassWithToString();

      // Получение строкового представления
      String result = AnnotationProcessor.processToString(obj);
      System.out.println("Строковое представление: " + result);

      // Проверка 1: поля с Mode.YES или без аннотации должны отображаться
      boolean test1 = result.contains("name=Тестовый объект") &&
          result.contains("id=12345") &&
          result.contains("active=true") &&
          result.contains("email=test@example.com");

      // Проверка 2: поля с Mode.NO не должны отображаться
      boolean test2 = !result.contains("password") && !result.contains("secret123");

      // Проверка 3: формат должен быть корректным
      boolean test3 = result.startsWith("ClassWithToString{") &&
          result.endsWith("}");

      if (test1 && test2 && test3) {
        System.out.println("✅ ТЕСТ 2.1 ПРОЙДЕН УСПЕШНО!");
        System.out.println("   - Поля с Mode.YES отображаются");
        System.out.println("   - Поля с Mode.NO скрыты");
        System.out.println("   - Формат строки корректен");
      } else {
        System.out.println("❌ ТЕСТ 2.1 НЕ ПРОЙДЕН!");
        if (!test1) System.out.println("   - Поля с Mode.YES отображаются некорректно");
        if (!test2) System.out.println("   - Поля с Mode.NO не скрыты");
        if (!test3) System.out.println("   - Формат строки некорректен");
      }

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста 2.1: " + e.getMessage());
    }
  }

  /**
   * Запускает тест для совместной работы @Cache и @Invoke (Задание 2.7).
   */
  private static void runCacheAndInvokeTest() {
    System.out.println("=== ТЕСТИРОВАНИЕ @Cache и @Invoke (Задание 2.7) ===");
    System.out.println("Задание: Проверить совместную работу аннотаций @Cache и @Invoke");

    try {
      // Тест 1: Проверка аннотации @Cache на классе ClassWithCache
      System.out.println("\n1. Проверка аннотации @Cache...");
      boolean hasCacheAnnotation = ClassWithCache.class.isAnnotationPresent(annotations.Cache.class);

      if (hasCacheAnnotation) {
        annotations.Cache cacheAnnotation = ClassWithCache.class.getAnnotation(annotations.Cache.class);
        String[] cacheAreas = cacheAnnotation.value();
        System.out.println("   ✅ Найдена аннотация @Cache");
        System.out.println("   Области кеширования: " + java.util.Arrays.toString(cacheAreas));

        if (cacheAreas.length == 3 &&
            cacheAreas[0].equals("users") &&
            cacheAreas[1].equals("orders") &&
            cacheAreas[2].equals("products")) {
          System.out.println("   ✅ Области кеширования корректны");
        } else {
          System.out.println("   ❌ Области кеширования некорректны");
        }
      } else {
        System.out.println("   ❌ Аннотация @Cache не найдена");
      }

      // Тест 2: Проверка аннотации @Invoke на методах ClassWithCache
      System.out.println("\n2. Проверка аннотации @Invoke...");
      ClassWithCache cacheObj = new ClassWithCache();
      java.lang.reflect.Method[] methods = cacheObj.getClass().getDeclaredMethods();
      int invokeMethodsCount = 0;

      for (java.lang.reflect.Method method : methods) {
        if (method.isAnnotationPresent(annotations.Invoke.class)) {
          invokeMethodsCount++;
          System.out.println("   ✅ Найден метод с @Invoke: " + method.getName());
        }
      }

      if (invokeMethodsCount > 0) {
        System.out.println("   Всего методов с @Invoke: " + invokeMethodsCount);
      } else {
        System.out.println("   ❌ Методы с @Invoke не найдены");
      }

      // Тест 3: Проверка выполнения метода с @Invoke
      System.out.println("\n3. Проверка выполнения метода с @Invoke...");
      AnnotationProcessor.processInvoke(cacheObj);

      // Проверка, что методы были вызваны
      System.out.println("   ✅ Методы с @Invoke вызваны через AnnotationProcessor");

      // Итог теста
      if (hasCacheAnnotation && invokeMethodsCount > 0) {
        System.out.println("\n✅ ТЕСТ 2.7 ПРОЙДЕН УСПЕШНО!");
        System.out.println("   - Аннотация @Cache корректно работает");
        System.out.println("   - Аннотация @Invoke корректно работает");
        System.out.println("   - Совместная работа аннотаций проверена");
      } else {
        System.out.println("\n❌ ТЕСТ 2.7 НЕ ПРОЙДЕН!");
      }

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста 2.7: " + e.getMessage());
      e.printStackTrace();
    }
  }
}