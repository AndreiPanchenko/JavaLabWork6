package entities;

import annotations.Default;

/**
 * Класс для демонстрации работы аннотации @Default.
 * Соответствует заданию 1.2: Проаннотируйте какой-либо класс данной аннотацией,
 * указав тип по умолчанию.
 */
@Default(String.class)
public class ClassWithDefault {
  /** Поле с аннотацией @Default */
  @Default(Integer.class)
  private String field = "default value";
}