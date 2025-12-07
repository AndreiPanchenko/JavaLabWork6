package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация с двумя обязательными свойствами.
 * Соответствует заданию 1.5: Разработать аннотацию @Two с двумя обязательными свойствами.
 * Цель: только ТИП (классы)
 * Доступна во время исполнения программы (RUNTIME)
 * Имеет два обязательных свойства: first типа String и second типа int
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Two {
  /**
   * Строковое свойство first
   * @return строковое значение
   */
  String first();

  /**
   * Целочисленное свойство second
   * @return целочисленное значение
   */
  int second();
}