import entities.ClassWithToString;
import processors.AnnotationProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки работы аннотации @ToString.
 * Содержит тесты для проверки корректности формирования строкового представления объектов.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 * @see annotations.ToString
 */
class ToStringTest {

  /**
   * Основной тест для проверки обработки аннотации @ToString.
   * Проверяет корректность включения и исключения полей в строковое представление.
   */
  @Test
  @DisplayName("--- Обработка @ToString ---")
  void testToStringAnnotationProcessing() {
    System.out.println("=== Тестирование @ToString ===");
    System.out.println("Цель: Проверить корректность обработки аннотации @ToString");
    System.out.println();

    // Тест 1: Проверка включения полей с Mode.YES или без аннотации
    System.out.println("Тест 1: Проверка отображения полей с @ToString(Mode.YES) или без аннотации");
    ClassWithToString obj1 = new ClassWithToString();
    String result1 = AnnotationProcessor.processToString(obj1);

    assertAll("Поля с Mode.YES или без аннотации должны отображаться",
        () -> {
          System.out.println("Проверка поля 'name'...");
          assertTrue(result1.contains("name=Тестовый объект"));
          System.out.println("Поле 'name' отображается (без аннотации = по умолчанию YES)");
        },
        () -> {
          System.out.println("Проверка поля 'id'...");
          assertTrue(result1.contains("id=12345"));
          System.out.println("Поле 'id' отображается (без аннотации = по умолчанию YES)");
        },
        () -> {
          System.out.println("Проверка поля 'active'...");
          assertTrue(result1.contains("active=true"));
          System.out.println("Поле 'active' отображается (без аннотации = по умолчанию YES)");
        },
        () -> {
          System.out.println("Проверка поля 'email'...");
          assertTrue(result1.contains("email=test@example.com"));
          System.out.println("Поле 'email' отображается (явно аннотировано с Mode.YES)");
        }
    );

    System.out.println("✅ Тест 1 пройден: поля с Mode.YES или без аннотации корректно отображаются");
    System.out.println();

    // Тест 2: Проверка исключения полей с Mode.NO
    System.out.println("Тест 2: Проверка исключения полей с @ToString(Mode.NO)");
    ClassWithToString obj2 = new ClassWithToString();
    String result2 = AnnotationProcessor.processToString(obj2);

    assertAll("Поля с Mode.NO не должны отображаться",
        () -> {
          System.out.println("Проверка поля 'password'...");
          assertFalse(result2.contains("password"));
          System.out.println("Поле 'password' не отображается (аннотировано с Mode.NO)");
        },
        () -> {
          System.out.println("Проверка значения 'secret123'...");
          assertFalse(result2.contains("secret123"));
          System.out.println("Значение поля 'password' не отображается");
        }
    );

    System.out.println("✅ Тест 2 пройден: поля с Mode.NO корректно исключены из вывода");
    System.out.println();

    // Тест 3: Проверка формата вывода
    System.out.println("Тест 3: Проверка формата строкового представления");
    ClassWithToString obj3 = new ClassWithToString();
    String result3 = AnnotationProcessor.processToString(obj3);

    assertAll("Формат строкового представления должен быть корректным",
        () -> {
          System.out.println("Проверка начала строки...");
          assertTrue(result3.startsWith("ClassWithToString{"));
          System.out.println("Строка начинается с имени класса");
        },
        () -> {
          System.out.println("Проверка конца строки...");
          assertTrue(result3.endsWith("}"));
          System.out.println("Строка заканчивается фигурной скобкой");
        },
        () -> {
          System.out.println("Проверка формата полей...");
          assertTrue(result3.contains("="));
          System.out.println("Строка содержит разделитель '=' между именами и значениями полей");
        }
    );

    System.out.println("✅ Тест 3 пройден: формат строкового представления корректен");
    System.out.println();

    // Итоговый вывод результата
    System.out.println("Итоговый результат toString:");
    System.out.println("  " + result3);
    System.out.println();
    System.out.println("Тестирование @ToString завершено успешно!");
  }

  /**
   * Тест для проверки обработки аннотации @ToString с пользовательскими значениями полей.
   * Проверяет корректность работы с различными значениями полей объекта.
   */
  @Test
  @DisplayName("--- Обработка @ToString с кастомными значениями ---")
  void testToStringWithCustomValues() {
    System.out.println("=== Тестирование @ToString с кастомными значениями ===");
    System.out.println("Цель: Проверить обработку аннотации с различными значениями полей");
    System.out.println();

    // Создаем объект с кастомными значениями
    ClassWithToString customObj = new ClassWithToString("Кастомное имя", 777, "скрытый-пароль", "custom@mail.ru");
    String result = AnnotationProcessor.processToString(customObj);

    System.out.println("Результат toString с кастомными значениями:");
    System.out.println("  " + result);
    System.out.println();

    assertAll("Кастомные значения должны корректно обрабатываться",
        () -> {
          assertTrue(result.contains("name=Кастомное имя"));
          System.out.println("Новое значение поля 'name' корректно отображается");
        },
        () -> {
          assertTrue(result.contains("id=777"));
          System.out.println("Новое значение поля 'id' корректно отображается");
        },
        () -> {
          assertTrue(result.contains("email=custom@mail.ru"));
          System.out.println("Новое значение поля 'email' корректно отображается (Mode.YES)");
        },
        () -> {
          assertFalse(result.contains("password"));
          System.out.println("Поле 'password' не отображается даже с новым значением");
        },
        () -> {
          assertFalse(result.contains("скрытый-пароль"));
          System.out.println("Новое значение поля 'password' не отображается (Mode.NO)");
        }
    );

    System.out.println();
    System.out.println("Тестирование @ToString с кастомными значениями завершено успешно!");
  }

  /**
   * Статический метод для запуска тестов из главного меню.
   * Создает экземпляр тестового класса и запускает основные тесты.
   */
  public static void runTest() {
    System.out.println("ЗАПУСК ТЕСТОВ @ToString");
    System.out.println("=========================================");

    ToStringTest testInstance = new ToStringTest();

    try {
      testInstance.testToStringAnnotationProcessing();
    } catch (Exception e) {
      System.out.println("❌ Ошибка в основном тесте: " + e.getMessage());
    }

    try {
      testInstance.testToStringWithCustomValues();
    } catch (Exception e) {
      System.out.println("❌ Ошибка в тесте с кастомными значениями: " + e.getMessage());
    }

    System.out.println("=========================================");
    System.out.println("✅ ВСЕ ТЕСТЫ @ToString ЗАВЕРШЕНЫ");
    System.out.println();
  }
}