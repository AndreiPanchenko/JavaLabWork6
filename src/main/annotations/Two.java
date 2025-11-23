package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация с двумя обязательными свойствами.
 * Может применяться только к типам (классам) и доступна во время выполнения.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Two {

  /**
   * Возвращает строковое значение свойства first.
   *
   * @return строковое значение
   */
  String first();

  /**
   * Возвращает целочисленное значение свойства second.
   *
   * @return целочисленное значение
   */
  int second();
}