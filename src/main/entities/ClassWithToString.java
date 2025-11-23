package entities;

import annotations.ToString;

/**
 * Класс для демонстрации работы аннотации @ToString.
 * Содержит поля с различными настройками аннотации @ToString.
 * Используется для тестирования формирования строкового представления объекта.
 *
 * @author Student
 * @version 1.0
 * @see annotations.ToString
 */
@ToString
public class ClassWithToString {

  /** Поле name без явной аннотации - используется значение по умолчанию YES */
  private String name = "Тестовый объект";

  /** Поле id без явной аннотации - используется значение по умолчанию YES */
  private int id = 12345;

  /** Поле active без явной аннотации - используется значение по умолчанию YES */
  private boolean active = true;

  /** Поле password с явным указанием NO - исключено из строкового представления */
  @ToString(ToString.Mode.NO)
  private String password = "secret123";

  /** Поле email с явным указанием YES - включено в строковое представление */
  @ToString(ToString.Mode.YES)
  private String email = "test@example.com";

  /**
   * Конструктор по умолчанию.
   */
  public ClassWithToString() {
  }

  /**
   * Конструктор с параметрами для инициализации всех полей.
   *
   * @param name имя объекта
   * @param id идентификатор объекта
   * @param password пароль (не отображается в toString)
   * @param email email (отображается в toString)
   */
  public ClassWithToString(String name, int id, String password, String email) {
    this.name = name;
    this.id = id;
    this.password = password;
    this.email = email;
  }
}