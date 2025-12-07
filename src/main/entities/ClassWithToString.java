package entities;

import annotations.ToString;

/**
 * Класс для демонстрации работы аннотации @ToString.
 * Соответствует заданию 1.3: Проаннотируйте класс аннотацией @ToString,
 * а одно из полей – с @ToString(Mode.NO).
 */
@ToString
public class ClassWithToString {
  /** Поле без аннотации - используется значение по умолчанию YES */
  private String name = "Тестовый объект";

  /** Поле без аннотации - используется значение по умолчанию YES */
  private int id = 12345;

  /** Поле без аннотации - используется значение по умолчанию YES */
  private boolean active = true;

  /** Поле с явным указанием NO - исключено из строкового представления */
  @ToString(ToString.Mode.NO)
  private String password = "secret123";

  /** Поле с явным указанием YES - включено в строковое представление */
  @ToString(ToString.Mode.YES)
  private String email = "test@example.com";
}