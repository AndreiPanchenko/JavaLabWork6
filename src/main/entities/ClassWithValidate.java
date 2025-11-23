package entities;

import annotations.Validate;

/**
 * Класс для демонстрации работы аннотации @Validate.
 * Содержит аннотацию с массивом классов для валидации.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 * @see annotations.Validate
 */
@Validate({String.class, Integer.class, Double.class, java.util.Date.class})
public class ClassWithValidate {

  /** Поле данных для валидации */
  private String data;

  /**
   * Конструктор по умолчанию.
   * Инициализирует поле data значением по умолчанию.
   */
  public ClassWithValidate() {
    this.data = "validation example";
  }
}