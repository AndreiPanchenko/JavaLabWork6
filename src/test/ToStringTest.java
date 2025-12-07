import entities.ClassWithToString;
import processors.AnnotationProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Тестовый класс для проверки работы аннотации @ToString.
 * Соответствует заданию 2.1: Напишите тест, используя фреймворк JUnit,
 * к методу, формирующему строковое представление объекта, основанное на аннотации @ToString.
 */
public class ToStringTest {

  private ClassWithToString testObject;
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  /**
   * Подготовка тестового объекта перед каждым тестом
   */
  @BeforeEach
  void setUp() {
    System.out.println("\n" + "=".repeat(60));
    System.out.println("Начало нового теста");
    testObject = new ClassWithToString();
    System.setOut(new PrintStream(outputStream));
  }

  /**
   * Тест проверяет, что поля с @ToString(Mode.YES) или без аннотации отображаются
   * Соответствует первой части задания 2.1
   */
  @Test
  @DisplayName("Проверка отображения полей с Mode.YES или без аннотации")
  void testFieldsWithYesOrNoAnnotationDisplayed() {
    System.out.println("=== ТЕСТ: Отображение полей с Mode.YES или без аннотации ===");

    try {
      String result = AnnotationProcessor.processToString(testObject);
      System.out.println("Сгенерированная строка: " + result);

      // Проверка отображения полей
      boolean testPassed = true;

      // Проверка поля без аннотации (по умолчанию YES)
      if (result.contains("name=Тестовый объект")) {
        System.out.println("✅ Поле 'name' (без аннотации) отображается корректно");
      } else {
        System.out.println("❌ ОШИБКА: Поле 'name' не отображается");
        testPassed = false;
      }

      // Проверка поля без аннотации (по умолчанию YES)
      if (result.contains("id=12345")) {
        System.out.println("✅ Поле 'id' (без аннотации) отображается корректно");
      } else {
        System.out.println("❌ ОШИБКА: Поле 'id' не отображается");
        testPassed = false;
      }

      // Проверка поля без аннотации (по умолчанию YES)
      if (result.contains("active=true")) {
        System.out.println("✅ Поле 'active' (без аннотации) отображается корректно");
      } else {
        System.out.println("❌ ОШИБКА: Поле 'active' не отображается");
        testPassed = false;
      }

      // Проверка поля с явной аннотацией @ToString(Mode.YES)
      if (result.contains("email=test@example.com")) {
        System.out.println("✅ Поле 'email' (с @ToString(Mode.YES)) отображается корректно");
      } else {
        System.out.println("❌ ОШИБКА: Поле 'email' не отображается");
        testPassed = false;
      }

      // Итоговый результат
      System.out.println("\n" + "=".repeat(40));
      if (testPassed) {
        System.out.println("✅ ТЕСТ ВЫПОЛНЕН УСПЕШНО!");
        System.out.println("Все поля с Mode.YES или без аннотации отображаются корректно");
      } else {
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Не все поля с Mode.YES или без аннотации отображаются");
      }

      assertTrue(testPassed, "Все поля с Mode.YES или без аннотации должны отображаться");

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста: " + e.getMessage());
      fail("Исключение при выполнении теста: " + e.getMessage());
    }
  }

  /**
   * Тест проверяет, что поля с @ToString(Mode.NO) не отображаются
   * Соответствует второй части задания 2.1
   */
  @Test
  @DisplayName("Проверка скрытия полей с Mode.NO")
  void testFieldsWithNoAnnotationHidden() {
    System.out.println("=== ТЕСТ: Скрытие полей с Mode.NO ===");

    try {
      String result = AnnotationProcessor.processToString(testObject);
      System.out.println("Сгенерированная строка: " + result);

      // Проверка скрытия полей
      boolean testPassed = true;

      // Проверка, что поле с @ToString(Mode.NO) не отображается
      if (!result.contains("password") && !result.contains("secret123")) {
        System.out.println("✅ Поле 'password' (с @ToString(Mode.NO)) правильно скрыто");
        System.out.println("  - Не найдено 'password' в строке");
        System.out.println("  - Не найдено значение 'secret123' в строке");
      } else {
        System.out.println("❌ ОШИБКА: Поле 'password' не должно отображаться");
        if (result.contains("password")) {
          System.out.println("  - Найдено 'password' в строке");
          testPassed = false;
        }
        if (result.contains("secret123")) {
          System.out.println("  - Найдено значение 'secret123' в строке");
          testPassed = false;
        }
      }

      // Итоговый результат
      System.out.println("\n" + "=".repeat(40));
      if (testPassed) {
        System.out.println("✅ ТЕСТ ВЫПОЛНЕН УСПЕШНО!");
        System.out.println("Поля с Mode.NO правильно скрыты");
      } else {
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Поля с Mode.NO не должны отображаться");
      }

      assertTrue(testPassed, "Поля с Mode.NO не должны отображаться");

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