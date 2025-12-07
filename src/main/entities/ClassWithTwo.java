package entities;

import annotations.Two;

/**
 * Класс для демонстрации работы аннотации @Two.
 * Соответствует заданию 1.5: Проаннотируйте какой-либо класс аннотацией @Two,
 * передав строковое и числовое значения.
 */
@Two(first = "Hello friend :)", second = 52)
public class ClassWithTwo {
  private String message = "Пример класса с двумя параметрами";

  /**
   * Конструктор по умолчанию
   */
  public ClassWithTwo() {
  }
}