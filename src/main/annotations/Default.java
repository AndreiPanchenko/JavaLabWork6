package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания класса по умолчанию.
 * Соответствует заданию 1.2: Разработать аннотацию @Default
 * Целью может быть ТИП или ПОЛЕ
 * Доступна во время исполнения программы (RUNTIME)
 * Имеет обязательное свойство value типа Class
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {
  /**
   * Класс по умолчанию
   * @return класс по умолчанию
   */
  Class<?> value();
}