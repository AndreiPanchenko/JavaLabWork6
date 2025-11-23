package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания класса по умолчанию.
 * Может применяться к типам (классам) и полям, доступна во время выполнения.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 *
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {

  /**
   * Возвращает класс, используемый по умолчанию.
   *
   * @return класс по умолчанию
   */
  Class<?> value();
}