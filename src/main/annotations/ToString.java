package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для управления строковым представлением объектов.
 * Соответствует заданию 1.3: Разработать аннотацию @ToString
 * Целью может быть ТИП или ПОЛЕ
 * Доступна во время исполнения программы (RUNTIME)
 * Имеет необязательное свойство value с двумя вариантами значений: YES или NO
 * Значение свойства по умолчанию: YES
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {
  /**
   * Режим отображения поля
   * @return режим отображения (по умолчанию YES)
   */
  Mode value() default Mode.YES;

  /**
   * Перечисление возможных режимов отображения полей
   */
  enum Mode {
    /** Поле включается в строковое представление */
    YES,
    /** Поле исключается из строкового представления */
    NO
  }
}