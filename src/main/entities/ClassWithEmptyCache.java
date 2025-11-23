package entities;

import annotations.Cache;

@Cache // пустой массив
public class ClassWithEmptyCache {
  public void someMethod() {
    System.out.println("Метод класса с пустым кешем");
  }
}