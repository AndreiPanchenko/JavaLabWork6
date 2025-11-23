package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания областей кеширования класса.
 * Может применяться только к типам (классам) и доступна во время выполнения.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {

  /**
   * Возвращает массив строк с названиями областей кеширования.
   * По умолчанию возвращает пустой массив.
   *
   * @return массив строк с названиями областей кеширования
   */
  String[] value() default {};
}