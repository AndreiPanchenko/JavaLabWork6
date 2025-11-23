package main;

import java.util.Scanner;

// Класс для безопасного ввода данных с обработкой ошибок.

public class InputChecker {

  private static final Scanner scanner = new Scanner(System.in);

  // === Методы для ввода целых чисел ===

  // Запрашивает у пользователя целое число с сообщением.

  public static int getInt(String prompt) {
    System.out.print(prompt);
    while (true) {
      try {
        return Integer.parseInt(scanner.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.print("Ошибка! Введите целое число: ");
      }
    }
  }

  // Запрашивает целое число без дополнительного сообщения.

  public static int getInt() {
    while (true) {
      try {
        return Integer.parseInt(scanner.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.print("Ошибка! Введите целое число: ");
      }
    }
  }

  // Запрашивает целое число в заданном диапазоне.

  public static int getIntInRange(String prompt, int min, int max) {
    System.out.print(prompt);
    while (true) {
      try {
        int value = Integer.parseInt(scanner.nextLine().trim());
        if (value < min || value > max) {
          System.out.print("Ошибка! Введите число от " + min + " до " + max + ": ");
          continue;
        }
        return value;
      } catch (NumberFormatException e) {
        System.out.print("Ошибка! Введите целое число: ");
      }
    }
  }

  // === Методы для ввода чисел с плавающей точкой ===

  // Запрашивает число double с сообщением.

  public static double getDouble(String prompt) {
    System.out.print(prompt);
    while (true) {
      try {
        return Double.parseDouble(scanner.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.print("Ошибка! Введите число: ");
      }
    }
  }

  // Запрашивает положительное число double.

  public static double getPositiveDouble(String prompt) {
    System.out.print(prompt);
    while (true) {
      try {
        double value = Double.parseDouble(scanner.nextLine().trim());
        if (value <= 0) {
          System.out.print("Ошибка! Введите положительное число: ");
          continue;
        }
        return value;
      } catch (NumberFormatException e) {
        System.out.print("Ошибка! Введите число: ");
      }
    }
  }

  // === Методы для ввода строк ===

  // Запрашивает строку с сообщением.

  public static String getString(String prompt) {
    System.out.print(prompt);
    return scanner.nextLine().trim();
  }

  // Запрашивает непустую строку.

  public static String getNonEmptyString(String prompt) {
    System.out.print(prompt);
    while (true) {
      String input = scanner.nextLine().trim();
      if (input.isEmpty()) {
        System.out.print("Ошибка! Введите непустую строку: ");
        continue;
      }
      return input;
    }
  }

  // === Базовые методы без prompt ===

  // Запрашивает число double без дополнительного сообщения.

  public static double getDouble() {
    while (true) {
      try {
        return Double.parseDouble(scanner.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.print("Ошибка! Введите число: ");
      }
    }
  }

  // Запрашивает строку без дополнительного сообщения.

  public static String getString() {
    return scanner.nextLine().trim();
  }

  // Закрывает Scanner (вызывать при завершении работы с классом).

  public static void close() {
    scanner.close();
  }
}