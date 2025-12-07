package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для автоматического вызова методов через Reflection API.
 * Соответствует заданию 1.1: Разработать аннотацию @Invoke
 * Целью может быть только МЕТОД
 * Доступна во время исполнения программы (RUNTIME)
 * Не имеет свойств
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Invoke {
  // Аннотация не имеет свойств
}