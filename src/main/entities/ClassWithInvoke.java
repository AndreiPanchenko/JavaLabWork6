package entities;

import annotations.Invoke;

/**
 * Класс для демонстрации работы аннотации @Invoke.
 * Содержит методы с аннотацией @Invoke, которые будут вызваны автоматически через Reflection.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 * @see annotations.Invoke
 */
public class ClassWithInvoke {

  /**
   * Публичный метод с аннотацией @Invoke.
   * Вызывается автоматически через Reflection API.
   */
  @Invoke
  public void annotatedMethod() {
    System.out.println("Метод с аннотацией @Invoke выполнен!");
  }

  /**
   * Обычный метод без аннотации.
   * Не вызывается автоматически.
   */
  public void normalMethod() {
    System.out.println("Обычный метод выполнен");
  }

  /**
   * Приватный метод с аннотацией @Invoke.
   * Вызывается автоматически через Reflection API с использованием setAccessible(true).
   */
  @Invoke
  private void privateAnnotatedMethod() {
    System.out.println("Приватный метод с @Invoke также выполнен!");
  }
}