package entities;

import annotations.Two;

/**
 * Класс для демонстрации работы аннотации @Two.
 * Содержит аннотацию с двумя обязательными свойствами разных типов.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 * @see annotations.Two
 */
@Two(first = "Hello friend :)", second = 52)
public class ClassWithTwo {

  /** Сообщение класса */
  private String message;

  /**
   * Конструктор по умолчанию.
   * Инициализирует поле message значением по умолчанию.
   */
  public ClassWithTwo() {
    this.message = "Пример класса с двумя параметрами";
  }
}