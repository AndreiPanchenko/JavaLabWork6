package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания областей кеширования класса.
 * Соответствует заданию 1.6: Разработать аннотацию @Cache
 * Целью может быть только ТИП
 * Доступна во время исполнения программы (RUNTIME)
 * Имеет необязательное свойство value, типа String[]
 * Значение свойства по умолчанию: пустой массив
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
  /**
   * Области кеширования
   * @return массив строк с названиями областей кеширования
   */
  String[] value() default {};
}