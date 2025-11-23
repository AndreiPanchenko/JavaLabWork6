package entities;

import annotations.Default;

@Default(String.class)
public class ClassWithDefault {

  @Default(Integer.class)
  private String field;

  public ClassWithDefault() {
    this.field = "default value";
  }
}