package entities;

import annotations.Invoke;

public class ClassWithInvoke {

  @Invoke
  public void annotatedMethod() {
    System.out.println("Метод с аннотацией @Invoke выполнен!");
  }

  public void normalMethod() {
    System.out.println("Обычный метод выполнен");
  }

  @Invoke
  private void privateAnnotatedMethod() {
    System.out.println("Приватный метод с @Invoke также выполнен!");
  }
}