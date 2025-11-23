package entities;

import annotations.ToString;

@ToString
public class ClassWithToString {

  private String name = "Тестовый объект";
  private int id = 12345;
  private boolean active = true;

  @ToString(ToString.Mode.NO)
  private String password = "secret123";

  @ToString(ToString.Mode.YES)
  private String email = "test@example.com";

  public ClassWithToString() {
  }

  public ClassWithToString(String name, int id, String password, String email) {
    this.name = name;
    this.id = id;
    this.password = password;
    this.email = email;
  }
}