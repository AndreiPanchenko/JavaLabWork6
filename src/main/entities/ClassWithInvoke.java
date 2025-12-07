package entities;

import annotations.Invoke;

/**
 * Класс для демонстрации работы аннотации @Invoke.
 * Соответствует заданию 1.1: Создать класс, содержащий несколько методов,
 * и проаннотируйте хотя бы один из них аннотацией @Invoke.
 */
public class ClassWithInvoke {
  /**
   * Метод с аннотацией @Invoke.
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
}