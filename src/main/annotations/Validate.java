package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания списка классов для валидации.
 * Может применяться к типам (классам) и другим аннотациям, доступна во время выполнения.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {

  /**
   * Возвращает массив классов для проверки валидации.
   *
   * @return массив классов для валидации
   */
  Class<?>[] value();
}