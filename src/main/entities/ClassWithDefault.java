package entities;

import annotations.Default;

/**
 * Класс для демонстрации работы аннотации @Default.
 * Содержит аннотации на уровне класса и поля.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 * @see annotations.Default
 */
@Default(String.class)
public class ClassWithDefault {

  /** Поле с аннотацией @Default */
  @Default(Integer.class)
  private String field;

  /**
   * Конструктор по умолчанию. Инициализирует поле значением по умолчанию.
   */
  public ClassWithDefault() {
    this.field = "default value";
  }
}