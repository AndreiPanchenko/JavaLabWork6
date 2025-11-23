package entities;

import annotations.Two;

@Two(first = "Hello friend :)", second = 52)
public class ClassWithTwo {

  private String message;

  public ClassWithTwo() {
    this.message = "Пример класса с двумя параметрами";
  }
}