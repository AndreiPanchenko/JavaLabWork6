package entities;

import annotations.Validate;

/**
 * Класс для демонстрации работы аннотации @Validate.
 * Соответствует заданию 1.4: Проаннотируйте класс аннотацией @Validate,
 * передав список типов для проверки.
 */
@Validate({String.class, Integer.class, Double.class})
public class ClassWithValidate {
  private String data = "validation example";
}