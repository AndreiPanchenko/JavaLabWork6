package main;

// Импорты остаются такими же

import entities.ClassWithCache;
import entities.ClassWithDefault;
import entities.ClassWithInvoke;
import entities.ClassWithToString;
import entities.ClassWithTwo;
import entities.ClassWithValidate;
import java.util.Scanner;
import processors.AnnotationProcessor;

public class Main {
  private static final Scanner scanner = new Scanner(System.in);

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
          runToStringTests();
          break;
        case 9:
          runCacheAndInvokeTests();
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

  private static void demonstrateInvoke() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @Invoke ===");
    System.out.println("Создаем объект ClassWithInvoke...");
    ClassWithInvoke obj = new ClassWithInvoke();
    AnnotationProcessor.processInvoke(obj);
    System.out.println("Методы с аннотацией @Invoke были автоматически вызваны");
  }

  private static void demonstrateDefault() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @Default ===");
    System.out.println("Анализируем класс ClassWithDefault...");
    AnnotationProcessor.processDefault(ClassWithDefault.class);
    System.out.println("Аннотация @Default обработана успешно");
  }

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

  private static void demonstrateValidate() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @Validate ===");
    System.out.println("Анализируем класс ClassWithValidate...");
    AnnotationProcessor.processValidate(ClassWithValidate.class);
    System.out.println("Аннотация @Validate обработана успешно");
  }

  private static void demonstrateTwo() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @Two ===");
    System.out.println("Анализируем класс ClassWithTwo...");
    AnnotationProcessor.processTwo(ClassWithTwo.class);
    System.out.println("Аннотация @Two обработана успешно");
  }

  private static void demonstrateCache() {
    System.out.println("=== ДЕМОНСТРАЦИЯ @Cache ===");
    System.out.println("Анализируем класс ClassWithCache...");
    AnnotationProcessor.processCache(ClassWithCache.class);
    System.out.println("Аннотация @Cache обработана успешно");
  }

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

  private static void runToStringTests() {
    System.out.println("=== ТЕСТИРОВАНИЕ @ToString ===");
    System.out.println("Для запуска JUnit тестов:");
    System.out.println("1. Откройте файл ToStringTest.java в папке tests");
    System.out.println("2. Нажмите зеленую стрелку рядом с классом");
    System.out.println("3. Или запустите через меню: Правой кнопкой → Run");
  }

  private static void runCacheAndInvokeTests() {
    System.out.println("=== ТЕСТИРОВАНИЕ @Cache и @Invoke ===");
    System.out.println("Для запуска JUnit тестов:");
    System.out.println("1. Откройте файл CacheAndInvokeTest.java в папке tests");
    System.out.println("2. Нажмите зеленую стрелку рядом с классом");
    System.out.println("3. Или запустите через меню: Правой кнопкой → Run");
  }
}