package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация для управления строковым представлением объектов.
 * Может применяться к типам (классам) и полям, доступна во время выполнения.
 * Определяет, какие поля должны включаться в строковое представление.
 *
 * @author Панченко Андрей ИТ-13
 * @version 1.0
 *
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {

  /**
   * Возвращает режим отображения поля в строковом представлении.
   * По умолчанию YES - поле отображается.
   *
   * @return режим отображения поля
   */
  Mode value() default Mode.YES;

  /**
   * Перечисление возможных режимов отображения полей.
   */
  public enum Mode {
    /** Поле включается в строковое представление */
    YES,
    /** Поле исключается из строкового представления */
    NO
  }
}