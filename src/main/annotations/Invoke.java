package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для автоматического вызова методов через Reflection API.
 * Может применяться только к методам и доступна во время выполнения.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Invoke {
  // Аннотация не имеет свойств
}