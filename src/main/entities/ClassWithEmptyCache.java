package entities;

import annotations.Cache;

/**
 * Класс для демонстрации работы аннотации @Cache с пустым массивом.
 * Используется для тестирования поведения при отсутствии указанных областей кеширования.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 * @see annotations.Cache
 */
@Cache // пустой массив
public class ClassWithEmptyCache {

  /**
   * Пример метода класса с пустым кешем.
   * Выводит сообщение в консоль.
   */
  public void someMethod() {
    System.out.println("Метод класса с пустым кешем");
  }
}