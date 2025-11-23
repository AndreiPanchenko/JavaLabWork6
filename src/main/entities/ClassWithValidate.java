package entities;

import annotations.Validate;

@Validate({String.class, Integer.class, Double.class, java.util.Date.class})
public class ClassWithValidate {

  private String data;

  public ClassWithValidate() {
    this.data = "validation example";
  }
}