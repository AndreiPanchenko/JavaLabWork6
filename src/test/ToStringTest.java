// ToStringTest.java
import entities.ClassWithToString;
import processors.AnnotationProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки работы аннотации @ToString.
 * Содержит тесты для проверки корректности формирования строкового представления объектов
 * в соответствии с заданием 2.1.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 * @see annotations.ToString
 */
class ToStringTest {

  /**
   * Тест для задания 2.1: проверка формирования строкового представления объекта.
   * Проверяет корректность включения и исключения полей в строковое представление
   * на основе аннотации @ToString.
   */
  @Test
  @DisplayName("Тестирование обработки аннотации @ToString")
  void testToStringAnnotationProcessing() {
    System.out.println("=== ТЕСТ 2.1: Проверка аннотации @ToString ===");
    System.out.println("Цель: Проверить корректность формирования строкового представления объекта");
    System.out.println();

    // Создание тестового объекта
    System.out.println("Создание тестового объекта...");
    ClassWithToString obj = new ClassWithToString();
    System.out.println("✅ Объект успешно создан");

    // Получение строкового представления
    System.out.println("Формирование строкового представления...");
    String result = AnnotationProcessor.processToString(obj);
    System.out.println("✅ Строковое представление сформировано");
    System.out.println();

    // Проверка включения полей с Mode.YES или без аннотации
    System.out.println("1. ПРОВЕРКА ВКЛЮЧЕНИЯ ПОЛЕЙ:");
    System.out.println("   Поля с @ToString(Mode.YES) или без аннотации должны отображаться");

    assertAll("Проверка отображения полей",
        () -> {
          System.out.print("   • Поле 'name' (без аннотации = по умолчанию YES)... ");
          assertTrue(result.contains("name=Тестовый объект"));
          System.out.println("✅ ОТОБРАЖАЕТСЯ");
        },
        () -> {
          System.out.print("   • Поле 'id' (без аннотации = по умолчанию YES)... ");
          assertTrue(result.contains("id=12345"));
          System.out.println("✅ ОТОБРАЖАЕТСЯ");
        },
        () -> {
          System.out.print("   • Поле 'active' (без аннотации = по умолчанию YES)... ");
          assertTrue(result.contains("active=true"));
          System.out.println("✅ ОТОБРАЖАЕТСЯ");
        },
        () -> {
          System.out.print("   • Поле 'email' (явно аннотировано с Mode.YES)... ");
          assertTrue(result.contains("email=test@example.com"));
          System.out.println("✅ ОТОБРАЖАЕТСЯ");
        }
    );

    System.out.println();

    // Проверка исключения полей с Mode.NO
    System.out.println("2. ПРОВЕРКА ИСКЛЮЧЕНИЯ ПОЛЕЙ:");
    System.out.println("   Поля с @ToString(Mode.NO) не должны отображаться");

    assertAll("Проверка исключения полей",
        () -> {
          System.out.print("   • Поле 'password' (аннотировано с Mode.NO)... ");
          assertFalse(result.contains("password"));
          System.out.println("✅ НЕ ОТОБРАЖАЕТСЯ");
        },
        () -> {
          System.out.print("   • Значение 'secret123' поля 'password'... ");
          assertFalse(result.contains("secret123"));
          System.out.println("✅ НЕ ОТОБРАЖАЕТСЯ");
        }
    );

    System.out.println();

    // Проверка формата вывода
    System.out.println("3. ПРОВЕРКА ФОРМАТА ВЫВОДА:");
    System.out.println("   Формат строкового представления должен быть корректным");

    assertAll("Проверка формата",
        () -> {
          System.out.print("   • Начало строки с имени класса... ");
          assertTrue(result.startsWith("ClassWithToString{"));
          System.out.println("✅ КОРРЕКТНО");
        },
        () -> {
          System.out.print("   • Конец строки фигурной скобкой... ");
          assertTrue(result.endsWith("}"));
          System.out.println("✅ КОРРЕКТНО");
        },
        () -> {
          System.out.print("   • Наличие разделителя '='... ");
          assertTrue(result.contains("="));
          System.out.println("✅ КОРРЕКТНО");
        }
    );

    System.out.println();

    // Итоговый вывод
    System.out.println("=== РЕЗУЛЬТАТ ТЕСТА 2.1 ===");
    System.out.println("Все проверки пройдены успешно!");
    System.out.println("Итоговое строковое представление:");
    System.out.println("   " + result);
    System.out.println();
    System.out.println("ТЕСТ 2.1 ЗАВЕРШЕН УСПЕШНО!");
  }
}
