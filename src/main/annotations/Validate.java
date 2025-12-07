package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для указания списка классов для валидации.
 * Соответствует заданию 1.4: Разработать аннотацию @Validate
 * Целью может быть ТИП или АННОТАЦИЯ
 * Доступна во время исполнения программы (RUNTIME)
 * Имеет обязательное свойство value, типа Class[]
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
  /**
   * Массив классов для проверки валидации
   * @return массив классов для валидации
   */
  Class<?>[] value();
}