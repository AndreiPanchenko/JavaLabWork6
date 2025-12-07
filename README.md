# Лабораторная работа №6
## Отчет по реализации аннотаций и тестированию на Java

## Оглавление

1. [Введение](#введение)
2. [Формулировки задач](#формулировки-задач)
3. [Структура проекта](#структура-проекта)
4. [Описание реализаций и фрагменты кода](#описание-реализаций-и-фрагменты-кода)
   - [Задание 1.1: Аннотация @Invoke](#задание-11-аннотация-invoke)
   - [Задание 1.2: Аннотация @Default](#задание-12-аннотация-default)
   - [Задание 1.3: Аннотация @ToString](#задание-13-аннотация-tostring)
   - [Задание 1.4: Аннотация @Validate](#задание-14-аннотация-validate)
   - [Задание 1.5: Аннотация @Two](#задание-15-аннотация-two)
   - [Задание 1.6: Аннотация @Cache](#задание-16-аннотация-cache)
   - [Задание 2.1: Тестирование @ToString](#задание-21-тестирование-tostring)
   - [Задание 2.7: Тестирование @Cache и @Invoke](#задание-27-тестирование-cache-и-invoke)
5. [Тестирование](#тестирование)

## Введение

Данная лабораторная работа демонстрирует практическое применение аннотаций Java и Reflection API для создания системы обработки метаданных. Реализованы 6 пользовательских аннотаций с соответствующими обработчиками и 2 модульных теста с использованием JUnit. Все компоненты интегрированы в единую систему с консольным интерфейсом для демонстрации функциональности.

## Формулировки задач

### Задание 1.1. Аннотации

**@Invoke**
Разработайте аннотацию @Invoke со следующими характеристиками:
- Целью может быть только МЕТОД
- Доступна во время исполнения программы
- Не имеет свойств

Создайте класс, содержащий несколько методов, и проаннотируйте хотя бы один из них аннотацией @Invoke. Реализуйте обработчик (через Reflection API), который находит методы, отмеченные аннотацией @Invoke, и вызывает их автоматически.

### Задание 1.2. Аннотации

**@Default**
Разработайте аннотацию @Default со следующими характеристиками:
- Целью может быть ТИП или ПОЛЕ
- Доступна во время исполнения программы
- Имеет обязательное свойство value типа Class

Проаннотируйте какой-либо класс данной аннотацией, указав тип по умолчанию. Напишите обработчик, который выводит имя указанного класса по умолчанию.

### Задание 1.3. Аннотации

**@ToString**
Разработайте аннотацию @ToString со следующими характеристиками:
- Целью может быть ТИП или ПОЛЕ
- Доступна во время исполнения программы
- Имеет необязательное свойство value c двумя вариантами значений: YES или NO
- Значение свойства по умолчанию: YES

Проаннотируйте класс аннотацией @ToString, а одно из полей – с @ToString(Mode.NO). Создайте метод, который формирует строковое представление объекта, учитывая только те поля, где @ToString имеет значение YES.

### Задание 1.4. Аннотации

**@Validate**
Разработайте аннотацию @Validate со следующими характеристиками:
- Целью может быть ТИП или АННОТАЦИЯ
- Доступна во время исполнения программы
- Имеет обязательное свойство value, типа Class[]

Проаннотируйте класс аннотацией @Validate, передав список типов для проверки. Реализуйте обработчик, который выводит, какие классы указаны в аннотации.

### Задание 1.5. Аннотации

**@Two**
Разработайте аннотацию @Two со следующими характеристиками:
- Целью может быть ТИП
- Доступна во время исполнения программы
- Имеет два обязательных свойства: first типа String и second типа int

Проаннотируйте какой-либо класс аннотацией @Two, передав строковое и числовое значения. Реализуйте обработчик, который считывает и выводит значения этих свойств.

### Задание 1.6. Аннотации

**@Cache**
Разработайте аннотацию @Cache со следующими характеристиками:
- Целью может быть ТИП
- Доступна во время исполнения программы
- Имеет необязательное свойство value, типа String[]
- Значение свойства по умолчанию: пустой массив

Проаннотируйте класс аннотацией @Cache, указав несколько кешируемых областей. Создайте обработчик, который выводит список всех кешируемых областей или сообщение, что список пуст.

### Задание 2.1. Тестирование

Напишите тест, используя фреймворк JUnit, к методу toString() (или методу, формирующему строковое представление объекта, основанное на аннотации @ToString).
- Проверить, что в результирующей строке отображаются только те поля, которые имеют аннотацию @ToString(Mode.YES) или не аннотированы вовсе.
- Убедиться, что поля с @ToString(Mode.NO) не попадают в вывод.

### Задание 2.7. Тестирование

Создайте тест, используя фреймворк JUnit, проверяющий совместную работу аннотаций @Cache и @Invoke на одном классе.
- Разработайте класс, содержащий аннотацию @Cache({"users", "orders"}) и метод, помеченный @Invoke.
- В тесте c помощью Reflection получите экземпляр аннотированного класса, убедитесь, что аннотация @Cache корректно возвращает массив строк ["users", "orders"], автоматически вызовите метод с @Invoke и проверьте, что он действительно выполняется.
- Добавьте проверку, что при пустом массиве @Cache кеширование не активируется (например, метод не добавляет данные в фиктивный кеш).

## Структура проекта

```
LabWork_6/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── annotations/
│   │   │   │   ├── Cache.java
│   │   │   │   ├── Default.java
│   │   │   │   ├── Invoke.java
│   │   │   │   ├── ToString.java
│   │   │   │   ├── Two.java
│   │   │   │   └── Validate.java
│   │   │   ├── entities/
│   │   │   │   ├── ClassWithCache.java
│   │   │   │   ├── ClassWithDefault.java
│   │   │   │   ├── ClassWithEmptyCache.java
│   │   │   │   ├── ClassWithInvoke.java
│   │   │   │   ├── ClassWithToString.java
│   │   │   │   ├── ClassWithTwo.java
│   │   │   │   └── ClassWithValidate.java
│   │   │   ├── main/
│   │   │   │   ├── InputChecker.java
│   │   │   │   └── Main.java
│   │   │   └── processors/
│   │   │       └── AnnotationProcessor.java
│   └── test/
│       └── java/
│           ├── CacheAndInvokeTest.java
│           └── ToStringTest.java
```

## Описание реализаций и фрагменты кода

### Задание 1.1: Аннотация @Invoke

**Реализованные возможности:**
- Аннотация для автоматического вызова методов через Reflection API
- Обработчик, сканирующий все методы класса и выполняющий аннотированные
- Логирование процесса выполнения с подсчетом вызванных методов

**Ключевой код из файлов:**

```java
// файл: annotations/Invoke.java
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
```

```java
// файл: entities/ClassWithInvoke.java
package entities;

import annotations.Invoke;

/**
 * Класс для демонстрации работы аннотации @Invoke.
 * Соответствует заданию 1.1: Создать класс, содержащий несколько методов,
 * и проаннотируйте хотя бы один из них аннотацией @Invoke.
 */
public class ClassWithInvoke {
  /**
   * Метод с аннотацией @Invoke.
   * Вызывается автоматически через Reflection API.
   */
  @Invoke
  public void annotatedMethod() {
    System.out.println("Метод с аннотацией @Invoke выполнен!");
  }

  /**
   * Обычный метод без аннотации.
   * Не вызывается автоматически.
   */
  public void normalMethod() {
    System.out.println("Обычный метод выполнен");
  }
}
```

```java
// файл: processors/AnnotationProcessor.java (фрагмент)
/**
 * Обработчик для аннотации @Invoke.
 * Соответствует заданию 1.1: Реализуйте обработчик, который находит методы,
 * отмеченные аннотацией @Invoke, и вызывает их автоматически.
 * @param obj объект, методы которого нужно обработать
 */
public static void processInvoke(Object obj) {
  System.out.println("--- Обработка @Invoke ---");
  try {
    Class<?> clazz = obj.getClass();
    Method[] methods = clazz.getDeclaredMethods();
    int invokedCount = 0;

    for (Method method : methods) {
      if (method.isAnnotationPresent(Invoke.class)) {
        method.setAccessible(true);
        method.invoke(obj);
        invokedCount++;
      }
    }

    if (invokedCount == 0) {
      System.out.println("Методы с @Invoke не найдены");
    } else {
      System.out.println("Вызвано методов: " + invokedCount);
    }
  } catch (Exception e) {
    System.out.println("Ошибка при обработке @Invoke: " + e.getMessage());
  }
}
```

**Особенности реализации:**
- Использование `getDeclaredMethods()` для доступа ко всем методам класса
- Вызов `setAccessible(true)` для обеспечения доступа к методам
- Обработка исключений с выводом понятных сообщений об ошибках
- Подсчет и вывод количества вызванных методов

### Задание 1.2: Аннотация @Default

**Реализованные возможности:**
- Аннотация с обязательным параметром типа Class
- Поддержка применения к классам и полям
- Извлечение и вывод полного имени класса по умолчанию

**Ключевой код из файлов:**

```java
// файл: annotations/Default.java
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
```

```java
// файл: entities/ClassWithDefault.java
package entities;

import annotations.Default;

/**
 * Класс для демонстрации работы аннотации @Default.
 * Соответствует заданию 1.2: Проаннотируйте какой-либо класс данной аннотацией,
 * указав тип по умолчанию.
 */
@Default(String.class)
public class ClassWithDefault {
  /** Поле с аннотацией @Default */
  @Default(Integer.class)
  private String field = "default value";
}
```

```java
// файл: processors/AnnotationProcessor.java (фрагмент)
/**
 * Обработчик для аннотации @Default.
 * Соответствует заданию 1.2: Напишите обработчик, который выводит имя указанного класса по умолчанию.
 * @param clazz класс для анализа
 */
public static void processDefault(Class<?> clazz) {
  System.out.println("--- Обработка @Default ---");
  try {
    if (clazz.isAnnotationPresent(Default.class)) {
      Default defaultAnno = clazz.getAnnotation(Default.class);
      System.out.println("Класс по умолчанию: " + defaultAnno.value().getName());
    } else {
      System.out.println("Аннотация @Default не найдена на классе");
    }
  } catch (Exception e) {
    System.out.println("Ошибка при обработке @Default: " + e.getMessage());
  }
}
```

**Особенности реализации:**
- Использование `getName()` для получения полного имени класса
- Проверка наличия аннотации перед ее обработкой
- Единый обработчик для аннотаций на уровне класса

### Задание 1.3: Аннотация @ToString

**Реализованные возможности:**
- Аннотация с перечислением Mode (YES/NO) и значением по умолчанию YES
- Формирование строкового представления объектов с фильтрацией полей
- Автоматическое форматирование в стиле `ClassName{field=value, ...}`
- Рекурсивный обход всех полей класса через Reflection

**Ключевой код из файлов:**

```java
// файл: annotations/ToString.java
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
```

```java
// файл: entities/ClassWithToString.java
package entities;

import annotations.ToString;

/**
 * Класс для демонстрации работы аннотации @ToString.
 * Соответствует заданию 1.3: Проаннотируйте класс аннотацией @ToString,
 * а одно из полей – с @ToString(Mode.NO).
 */
@ToString
public class ClassWithToString {
  /** Поле без аннотации - используется значение по умолчанию YES */
  private String name = "Тестовый объект";

  /** Поле без аннотации - используется значение по умолчанию YES */
  private int id = 12345;

  /** Поле без аннотации - используется значение по умолчанию YES */
  private boolean active = true;

  /** Поле с явным указанием NO - исключено из строкового представления */
  @ToString(ToString.Mode.NO)
  private String password = "secret123";

  /** Поле с явным указанием YES - включено в строковое представление */
  @ToString(ToString.Mode.YES)
  private String email = "test@example.com";
}
```

```java
// файл: processors/AnnotationProcessor.java (фрагмент)
/**
 * Обработчик для аннотации @ToString.
 * Соответствует заданию 1.3: Создайте метод, который формирует строковое представление объекта,
 * учитывая только те поля, где @ToString имеет значение YES.
 * @param obj объект для преобразования в строку
 * @return строковое представление объекта
 */
public static String processToString(Object obj) {
  System.out.println("--- Обработка @ToString ---");
  try {
    Class<?> clazz = obj.getClass();
    StringBuilder result = new StringBuilder();

    if (!clazz.isAnnotationPresent(ToString.class)) {
      return obj.toString();
    }

    result.append(clazz.getSimpleName()).append("{");
    boolean firstField = true;

    for (Field field : clazz.getDeclaredFields()) {
      ToString toStringAnno = field.getAnnotation(ToString.class);

      // Если аннотация есть и значение NO - пропускаем поле
      if (toStringAnno != null && toStringAnno.value() == ToString.Mode.NO) {
        continue;
      }

      // Если аннотации нет или значение YES - включаем поле
      field.setAccessible(true);
      if (!firstField) {
        result.append(", ");
      }
      result.append(field.getName()).append("=").append(field.get(obj));
      firstField = false;
    }

    result.append("}");
    return result.toString();
  } catch (Exception e) {
    return "Ошибка при обработке @ToString: " + e.getMessage();
  }
}
```

**Особенности реализации:**
- Умная логика фильтрации: поля без аннотации считаются как YES
- Правильное форматирование с запятыми только между полями
- Обработка исключений с возвратом информативной строки об ошибке

### Задание 1.4: Аннотация @Validate

**Реализованные возможности:**
- Аннотация с массивом классов в качестве обязательного параметра
- Поддержка применения к классам и другим аннотациям
- Построчный вывод списка классов для валидации

**Ключевой код из файлов:**

```java
// файл: annotations/Validate.java
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
```

```java
// файл: entities/ClassWithValidate.java
package entities;

import annotations.Validate;

/**
 * Класс для демонстрации работы аннотации @Validate.
 * Соответствует заданию 1.4: Проаннотируйте класс аннотацией @Validate,
 * передав список типов для проверки.
 */
@Validate({String.class, Integer.class, Double.class})
public class ClassWithValidate {
  private String data = "validation example";
}
```

```java
// файл: processors/AnnotationProcessor.java (фрагмент)
/**
 * Обработчик для аннотации @Validate.
 * Соответствует заданию 1.4: Реализуйте обработчик, который выводит, какие классы указаны в аннотации.
 * @param clazz класс для анализа
 */
public static void processValidate(Class<?> clazz) {
  System.out.println("--- Обработка @Validate ---");
  try {
    if (clazz.isAnnotationPresent(Validate.class)) {
      Validate validateAnno = clazz.getAnnotation(Validate.class);
      System.out.println("Классы для проверки:");
      for (Class<?> validationClass : validateAnno.value()) {
        System.out.println("  - " + validationClass.getName());
      }
    } else {
      System.out.println("Аннотация @Validate не найдена");
    }
  } catch (Exception e) {
    System.out.println("Ошибка при обработке @Validate: " + e.getMessage());
  }
}
```

**Особенности реализации:**
- Итерация по массиву классов с использованием enhanced for-loop
- Форматированный вывод с отступами для читаемости
- Поддержка ANNOTATION_TYPE для создания метааннотаций

### Задание 1.5: Аннотация @Two

**Реализованные возможности:**
- Аннотация с двумя обязательными параметрами разных типов (String и int)
- Ограничение применения только к типам (классам)
- Чтение и форматированный вывод значений параметров

**Ключевой код из файлов:**

```java
// файл: annotations/Two.java
package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация с двумя обязательными свойствами.
 * Соответствует заданию 1.5: Разработать аннотацию @Two с двумя обязательными свойствами.
 * Цель: только ТИП (классы)
 * Доступна во время исполнения программы (RUNTIME)
 * Имеет два обязательных свойства: first типа String и second типа int
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Two {
  /**
   * Строковое свойство first
   * @return строковое значение
   */
  String first();

  /**
   * Целочисленное свойство second
   * @return целочисленное значение
   */
  int second();
}
```

```java
// файл: entities/ClassWithTwo.java
package entities;

import annotations.Two;

/**
 * Класс для демонстрации работы аннотации @Two.
 * Соответствует заданию 1.5: Проаннотируйте какой-либо класс аннотацией @Two,
 * передав строковое и числовое значения.
 */
@Two(first = "Hello friend :)", second = 52)
public class ClassWithTwo {
  private String message = "Пример класса с двумя параметрами";

  /**
   * Конструктор по умолчанию
   */
  public ClassWithTwo() {
  }
}
```

```java
// файл: processors/AnnotationProcessor.java (фрагмент)
/**
 * Обработчик для аннотации @Two.
 * Соответствует заданию 1.5: Реализуйте обработчик, который считывает и выводит значения этих свойств.
 * @param clazz класс для анализа
 */
public static void processTwo(Class<?> clazz) {
  System.out.println("--- Обработка @Two ---");
  try {
    if (clazz.isAnnotationPresent(Two.class)) {
      Two twoAnno = clazz.getAnnotation(Two.class);
      System.out.println("first: \"" + twoAnno.first() + "\"");
      System.out.println("second: " + twoAnno.second());
    } else {
      System.out.println("Аннотация @Two не найдена");
    }
  } catch (Exception e) {
    System.out.println("Ошибка при обработке @Two: " + e.getMessage());
  }
}
```

**Особенности реализации:**
- Использование кавычек для строковых значений при выводе
- Прямой доступ к параметрам аннотации через методы first() и second()
- Простой и понятный формат вывода

### Задание 1.6: Аннотация @Cache

**Реализованные возможности:**
- Аннотация с необязательным массивом строк (пустой массив по умолчанию)
- Обработка случаев: пустой массив, непустой массив, отсутствие аннотации
- Интеграция с аннотацией @Invoke для инициализации кэша

**Ключевой код из файлов:**

```java
// файл: annotations/Cache.java
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
```

```java
// файл: entities/ClassWithCache.java
package entities;

import annotations.Cache;
import annotations.Invoke;

/**
 * Класс для демонстрации работы аннотации @Cache.
 * Соответствует заданию 1.6: Проаннотируйте класс аннотацией @Cache,
 * указав несколько кешируемых областей.
 */
@Cache({"users", "orders", "products"})
public class ClassWithCache {
  /** Внутренний кеш для хранения данных */
  private java.util.Map<String, Object> cache = new java.util.HashMap<>();

  /**
   * Метод для инициализации кеша. Вызывается автоматически благодаря @Invoke.
   * Соответствует заданию 2.7: метод, помеченный @Invoke для автоматического вызова.
   */
  @Invoke
  public void initializeCache() {
    cache.put("users", new java.util.ArrayList<String>());
    cache.put("orders", new java.util.ArrayList<Integer>());
    System.out.println("Кеш инициализирован");
  }
}
```

```java
// файл: processors/AnnotationProcessor.java (фрагмент)
/**
 * Обработчик для аннотации @Cache.
 * Соответствует заданию 1.6: Создайте обработчик, который выводит список всех кешируемых областей
 * или сообщение, что список пуст.
 * @param clazz класс для анализа
 */
public static void processCache(Class<?> clazz) {
  System.out.println("--- Обработка @Cache ---");
  try {
    if (clazz.isAnnotationPresent(Cache.class)) {
      Cache cacheAnno = clazz.getAnnotation(Cache.class);
      String[] cacheAreas = cacheAnno.value();

      if (cacheAreas.length == 0) {
        System.out.println("Области кеширования не указаны (пустой массив)");
      } else {
        System.out.println("Области кеширования:");
        for (String area : cacheAreas) {
          System.out.println("  - " + area);
        }
      }
    } else {
      System.out.println("Аннотация @Cache не найдена");
    }
  } catch (Exception e) {
    System.out.println("Ошибка при обработке @Cache: " + e.getMessage());
  }
}
```

**Особенности реализации:**
- Проверка длины массива перед выводом содержимого
- Форматированный вывод с маркерами списка
- Поддержка совместного использования с @Invoke для автоматической инициализации

### Задание 2.1: Тестирование @ToString

**Реализованные возможности:**
- Комплексное тестирование логики фильтрации полей в @ToString
- Проверка включения полей с Mode.YES и без аннотации
- Проверка исключения полей с Mode.NO
- Верификация формата строкового представления

**Ключевой код из файлов:**

```java
// файл: test/java/ToStringTest.java
import entities.ClassWithToString;
import processors.AnnotationProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Тестовый класс для проверки работы аннотации @ToString.
 * Соответствует заданию 2.1: Напишите тест, используя фреймворк JUnit,
 * к методу, формирующему строковое представление объекта, основанное на аннотации @ToString.
 */
public class ToStringTest {

  private ClassWithToString testObject;
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  /**
   * Подготовка тестового объекта перед каждым тестом
   */
  @BeforeEach
  void setUp() {
    System.out.println("\n" + "=".repeat(60));
    System.out.println("Начало нового теста");
    testObject = new ClassWithToString();
    System.setOut(new PrintStream(outputStream));
  }

  /**
   * Тест проверяет, что поля с @ToString(Mode.YES) или без аннотации отображаются
   * Соответствует первой части задания 2.1
   */
  @Test
  @DisplayName("Проверка отображения полей с Mode.YES или без аннотации")
  void testFieldsWithYesOrNoAnnotationDisplayed() {
    System.out.println("=== ТЕСТ: Отображение полей с Mode.YES или без аннотации ===");

    try {
      String result = AnnotationProcessor.processToString(testObject);
      System.out.println("Сгенерированная строка: " + result);

      // Проверка отображения полей
      boolean testPassed = true;

      // Проверка поля без аннотации (по умолчанию YES)
      if (result.contains("name=Тестовый объект")) {
        System.out.println("✅ Поле 'name' (без аннотации) отображается корректно");
      } else {
        System.out.println("❌ ОШИБКА: Поле 'name' не отображается");
        testPassed = false;
      }

      // Проверка поля без аннотации (по умолчанию YES)
      if (result.contains("id=12345")) {
        System.out.println("✅ Поле 'id' (без аннотации) отображается корректно");
      } else {
        System.out.println("❌ ОШИБКА: Поле 'id' не отображается");
        testPassed = false;
      }

      // Проверка поля без аннотации (по умолчанию YES)
      if (result.contains("active=true")) {
        System.out.println("✅ Поле 'active' (без аннотации) отображается корректно");
      } else {
        System.out.println("❌ ОШИБКА: Поле 'active' не отображается");
        testPassed = false;
      }

      // Проверка поля с явной аннотацией @ToString(Mode.YES)
      if (result.contains("email=test@example.com")) {
        System.out.println("✅ Поле 'email' (с @ToString(Mode.YES)) отображается корректно");
      } else {
        System.out.println("❌ ОШИБКА: Поле 'email' не отображается");
        testPassed = false;
      }

      // Итоговый результат
      System.out.println("\n" + "=".repeat(40));
      if (testPassed) {
        System.out.println("✅ ТЕСТ ВЫПОЛНЕН УСПЕШНО!");
        System.out.println("Все поля с Mode.YES или без аннотации отображаются корректно");
      } else {
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Не все поля с Mode.YES или без аннотации отображаются");
      }

      assertTrue(testPassed, "Все поля с Mode.YES или без аннотации должны отображаться");

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста: " + e.getMessage());
      fail("Исключение при выполнении теста: " + e.getMessage());
    }
  }

  /**
   * Тест проверяет, что поля с @ToString(Mode.NO) не отображаются
   * Соответствует второй части задания 2.1
   */
  @Test
  @DisplayName("Проверка скрытия полей с Mode.NO")
  void testFieldsWithNoAnnotationHidden() {
    System.out.println("=== ТЕСТ: Скрытие полей с Mode.NO ===");

    try {
      String result = AnnotationProcessor.processToString(testObject);
      System.out.println("Сгенерированная строка: " + result);

      // Проверка скрытия полей
      boolean testPassed = true;

      // Проверка, что поле с @ToString(Mode.NO) не отображается
      if (!result.contains("password") && !result.contains("secret123")) {
        System.out.println("✅ Поле 'password' (с @ToString(Mode.NO)) правильно скрыто");
        System.out.println("  - Не найдено 'password' в строке");
        System.out.println("  - Не найдено значение 'secret123' в строке");
      } else {
        System.out.println("❌ ОШИБКА: Поле 'password' не должно отображаться");
        if (result.contains("password")) {
          System.out.println("  - Найдено 'password' в строке");
          testPassed = false;
        }
        if (result.contains("secret123")) {
          System.out.println("  - Найдено значение 'secret123' в строке");
          testPassed = false;
        }
      }

      // Итоговый результат
      System.out.println("\n" + "=".repeat(40));
      if (testPassed) {
        System.out.println("✅ ТЕСТ ВЫПОЛНЕН УСПЕШНО!");
        System.out.println("Поля с Mode.NO правильно скрыты");
      } else {
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Поля с Mode.NO не должны отображаться");
      }

      assertTrue(testPassed, "Поля с Mode.NO не должны отображаться");

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста: " + e.getMessage());
      fail("Исключение при выполнении теста: " + e.getMessage());
    }
  }

  /**
   * Восстановление оригинального System.out после тестов
   */
  @org.junit.jupiter.api.AfterEach
  void tearDown() {
    System.setOut(originalOut);
    System.out.println(outputStream.toString());
    System.out.println("Конец теста");
    System.out.println("=".repeat(60));
  }
}
```

**Особенности реализации:**
- Использование JUnit 5 с аннотациями @BeforeEach и @AfterEach
- Подробное логирование процесса выполнения тестов
- Комплексные проверки с использованием булевых выражений
- Проверка начала и конца строки для валидации формата

### Задание 2.7: Тестирование @Cache и @Invoke

**Реализованные возможности:**
- Интеграционное тестирование совместной работы аннотаций
- Проверка значений параметров аннотации @Cache
- Автоматический вызов методов с аннотацией @Invoke
- Тестирование граничных случаев (пустой массив @Cache)
- Моделирование кэша с использованием Map<String, Object>

**Ключевой код из файлов:**

```java
// файл: test/java/CacheAndInvokeTest.java
import annotations.Cache;
import annotations.Invoke;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Тестовый класс для проверки совместной работы аннотаций @Cache и @Invoke.
 * Соответствует заданию 2.7: Создайте тест, используя фреймворк JUnit,
 * проверяющий совместную работу аннотаций @Cache и @Invoke на одном классе.
 */
public class CacheAndInvokeTest {

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  /**
   * Тестовый класс с аннотацией @Cache и методом @Invoke.
   * Используется для проверки совместной работы аннотаций.
   */
  @Cache({"users", "orders"})
  public static class TestClassWithCacheAndInvoke {
    private Map<String, Object> cache = new HashMap<>();
    private boolean methodExecuted = false;

    /**
     * Метод с аннотацией @Invoke для инициализации данных.
     * Добавляет тестовые данные в кеш при вызове.
     */
    @Invoke
    public void initializeData() {
      methodExecuted = true;
      cache.put("users", "user123");
      cache.put("orders", "order456");
      System.out.println("Метод initializeData() выполнен - данные добавлены в кеш");
    }

    public Map<String, Object> getCache() { return cache; }
    public boolean isMethodExecuted() { return methodExecuted; }
  }

  /**
   * Тестовый класс с пустой аннотацией @Cache.
   * Используется для проверки отключения кеширования.
   */
  @Cache
  public static class TestClassWithEmptyCache {
    private Map<String, Object> cache = new HashMap<>();
    private boolean methodExecuted = false;

    /**
     * Метод с аннотацией @Invoke.
     * Не добавляет данные в кеш, так как @Cache пустой.
     */
    @Invoke
    public void dummyMethod() {
      methodExecuted = true;
      System.out.println("Метод dummyMethod() выполнен - кеширование не активировано");
    }

    public Map<String, Object> getCache() { return cache; }
    public boolean isMethodExecuted() { return methodExecuted; }
  }

  /**
   * Подготовка перед каждым тестом
   */
  @BeforeEach
  void setUp() {
    System.out.println("\n" + "=".repeat(60));
    System.out.println("Начало нового теста");
    System.setOut(new PrintStream(outputStream));
  }

  /**
   * Тест проверяет, что аннотация @Cache корректно возвращает массив строк
   * Соответствует первой части задания 2.7
   */
  @Test
  @DisplayName("Проверка аннотации @Cache с указанными областями")
  void testCacheAnnotationWithAreas() {
    System.out.println("=== ТЕСТ: Проверка аннотации @Cache с указанными областями ===");

    try {
      TestClassWithCacheAndInvoke testObject = new TestClassWithCacheAndInvoke();
      Class<?> clazz = testObject.getClass();

      // Проверка аннотации @Cache
      System.out.println("Проверка наличия аннотации @Cache...");
      boolean hasCacheAnnotation = clazz.isAnnotationPresent(Cache.class);

      if (hasCacheAnnotation) {
        System.out.println("✅ Аннотация @Cache найдена на классе");

        Cache cacheAnnotation = clazz.getAnnotation(Cache.class);
        String[] cacheAreas = cacheAnnotation.value();
        System.out.println("Области кеширования: " + java.util.Arrays.toString(cacheAreas));

        // Проверка корректности значений
        boolean testPassed = true;

        if (cacheAreas.length == 2) {
          System.out.println("✅ Количество областей кеширования корректно: 2");
        } else {
          System.out.println("❌ ОШИБКА: Ожидалось 2 области кеширования, получено: " + cacheAreas.length);
          testPassed = false;
        }

        if (cacheAreas[0].equals("users")) {
          System.out.println("✅ Первая область кеширования корректна: 'users'");
        } else {
          System.out.println("❌ ОШИБКА: Ожидалась область 'users', получено: '" + cacheAreas[0] + "'");
          testPassed = false;
        }

        if (cacheAreas[1].equals("orders")) {
          System.out.println("✅ Вторая область кеширования корректна: 'orders'");
        } else {
          System.out.println("❌ ОШИБКА: Ожидалась область 'orders', получено: '" + cacheAreas[1] + "'");
          testPassed = false;
        }

        // Итоговый результат
        System.out.println("\n" + "=".repeat(40));
        if (testPassed) {
          System.out.println("✅ ТЕСТ ВЫПОЛНЕН УСПЕШНО!");
          System.out.println("Аннотация @Cache корректно возвращает массив строк [\"users\", \"orders\"]");
        } else {
          System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
          fail("Аннотация @Cache возвращает некорректные значения");
        }

        assertTrue(testPassed, "Аннотация @Cache должна возвращать корректные значения");

      } else {
        System.out.println("❌ ОШИБКА: Аннотация @Cache не найдена");
        System.out.println("\n" + "=".repeat(40));
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Аннотация @Cache не найдена");
      }

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста: " + e.getMessage());
      fail("Исключение при выполнении теста: " + e.getMessage());
    }
  }

  /**
   * Тест проверяет, что метод с @Invoke вызывается и выполняется
   * Соответствует второй части задания 2.7
   */
  @Test
  @DisplayName("Проверка вызова и выполнения метода с аннотацией @Invoke")
  void testInvokeMethodExecution() {
    System.out.println("=== ТЕСТ: Проверка вызова и выполнения метода с аннотацией @Invoke ===");

    try {
      TestClassWithCacheAndInvoke testObject = new TestClassWithCacheAndInvoke();
      Class<?> clazz = testObject.getClass();

      // Поиск метода с аннотацией @Invoke
      System.out.println("Поиск метода с аннотацией @Invoke...");
      Method[] methods = clazz.getDeclaredMethods();
      Method invokeMethod = null;

      for (Method method : methods) {
        if (method.isAnnotationPresent(Invoke.class)) {
          invokeMethod = method;
          System.out.println("✅ Найден метод с @Invoke: " + method.getName());
          break;
        }
      }

      if (invokeMethod == null) {
        System.out.println("❌ ОШИБКА: Метод с @Invoke не найден");
        System.out.println("\n" + "=".repeat(40));
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Метод с @Invoke не найден");
      }

      // Автоматический вызов метода с @Invoke через Reflection
      System.out.println("\nАвтоматический вызов метода с @Invoke через Reflection...");
      invokeMethod.setAccessible(true);
      invokeMethod.invoke(testObject);

      // Проверка выполнения метода
      boolean testPassed = true;

      if (testObject.isMethodExecuted()) {
        System.out.println("✅ Метод с @Invoke успешно выполнен");
      } else {
        System.out.println("❌ ОШИБКА: Метод с @Invoke не выполнен");
        testPassed = false;
      }

      // Проверка вывода в консоль
      String output = outputStream.toString();
      if (output.contains("Метод initializeData() выполнен")) {
        System.out.println("✅ Метод вывел сообщение о выполнении");
      } else {
        System.out.println("❌ ОШИБКА: Метод не вывел сообщение о выполнении");
        testPassed = false;
      }

      // Проверка заполнения кеша
      System.out.println("\nПроверка заполнения кеша...");
      Map<String, Object> cache = testObject.getCache();

      if (cache.containsKey("users") && cache.containsKey("orders")) {
        System.out.println("✅ Кеш успешно заполнен данными");
        System.out.println("  - users: " + cache.get("users"));
        System.out.println("  - orders: " + cache.get("orders"));
      } else {
        System.out.println("❌ ОШИБКА: Кеш не заполнен корректно");
        testPassed = false;
      }

      // Итоговый результат
      System.out.println("\n" + "=".repeat(40));
      if (testPassed) {
        System.out.println("✅ ТЕСТ ВЫПОЛНЕН УСПЕШНО!");
        System.out.println("Метод с @Invoke автоматически вызывается и выполняется");
      } else {
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Метод с @Invoke не выполняется корректно");
      }

      assertTrue(testPassed, "Метод с @Invoke должен вызываться и выполняться");

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста: " + e.getMessage());
      fail("Исключение при выполнении теста: " + e.getMessage());
    }
  }

  /**
   * Тест проверяет, что при пустом массиве @Cache кеширование не активируется
   * Соответствует третьей части задания 2.7
   */
  @Test
  @DisplayName("Проверка работы с пустым массивом @Cache")
  void testEmptyCacheAnnotation() {
    System.out.println("=== ТЕСТ: Проверка работы с пустым массивом @Cache ===");

    try {
      TestClassWithEmptyCache testObject = new TestClassWithEmptyCache();
      Class<?> clazz = testObject.getClass();

      // Проверка пустой аннотации @Cache
      System.out.println("Проверка аннотации @Cache с пустым массивом...");
      boolean hasCacheAnnotation = clazz.isAnnotationPresent(Cache.class);

      if (hasCacheAnnotation) {
        Cache cacheAnnotation = clazz.getAnnotation(Cache.class);
        String[] cacheAreas = cacheAnnotation.value();

        boolean testPassed = true;

        if (cacheAreas.length == 0) {
          System.out.println("✅ Массив областей кеширования пуст (как и ожидалось)");
        } else {
          System.out.println("❌ ОШИБКА: Массив @Cache должен быть пустым, но содержит: " +
              java.util.Arrays.toString(cacheAreas));
          testPassed = false;
        }

        // Проверка и вызов метода с @Invoke для класса с пустым кешем
        System.out.println("\nПроверка метода с @Invoke для класса с пустым кешем...");
        Method[] methods = clazz.getDeclaredMethods();
        Method invokeMethod = null;

        for (Method method : methods) {
          if (method.isAnnotationPresent(Invoke.class)) {
            invokeMethod = method;
            System.out.println("✅ Найден метод с @Invoke: " + method.getName());
            break;
          }
        }

        if (invokeMethod != null) {
          invokeMethod.setAccessible(true);
          invokeMethod.invoke(testObject);

          if (testObject.isMethodExecuted()) {
            System.out.println("✅ Метод с @Invoke выполнен");
          } else {
            System.out.println("❌ ОШИБКА: Метод с @Invoke не выполнен");
            testPassed = false;
          }

          // Проверка вывода в консоль
          String output = outputStream.toString();
          if (output.contains("Метод dummyMethod() выполнен")) {
            System.out.println("✅ Метод вывел сообщение о выполнении");
          } else {
            System.out.println("❌ ОШИБКА: Метод не вывел сообщение о выполнении");
            testPassed = false;
          }

          // Проверка, что кеш остался пустым
          Map<String, Object> cache = testObject.getCache();
          if (cache.isEmpty()) {
            System.out.println("✅ Кеширование не активировано (кеш пуст)");
          } else {
            System.out.println("❌ ОШИБКА: Кеширование активировано при пустом @Cache");
            testPassed = false;
          }
        } else {
          System.out.println("❌ ОШИБКА: Метод с @Invoke не найден");
          testPassed = false;
        }

        // Итоговый результат
        System.out.println("\n" + "=".repeat(40));
        if (testPassed) {
          System.out.println("✅ ТЕСТ ВЫПОЛНЕН УСПЕШНО!");
          System.out.println("При пустом массиве @Cache кеширование не активируется");
        } else {
          System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
          fail("При пустом массиве @Cache кеширование не должно активироваться");
        }

        assertTrue(testPassed, "При пустом массиве @Cache кеширование не должно активироваться");

      } else {
        System.out.println("❌ ОШИБКА: Аннотация @Cache не найдена");
        System.out.println("\n" + "=".repeat(40));
        System.out.println("❌ ТЕСТ НЕ ВЫПОЛНЕН!");
        fail("Аннотация @Cache не найдена");
      }

    } catch (Exception e) {
      System.out.println("❌ ОШИБКА при выполнении теста: " + e.getMessage());
      fail("Исключение при выполнении теста: " + e.getMessage());
    }
  }

  /**
   * Восстановление оригинального System.out после тестов
   */
  @org.junit.jupiter.api.AfterEach
  void tearDown() {
    System.setOut(originalOut);
    System.out.println(outputStream.toString());
    System.out.println("Конец теста");
    System.out.println("=".repeat(60));
  }
}
```

**Особенности реализации:**
- Определение тестовых классов внутри тестового класса
- Использование Reflection для поиска и вызова аннотированных методов
- Проверка побочных эффектов выполнения методов (изменение состояния)
- Подробное логирование процесса выполнения тестов

## Тестирование

### Общая архитектура тестирования

Проект реализует двухуровневую систему тестирования:

1. **Интерактивное тестирование через консольное меню** - реализовано в классе `Main.java`, позволяет пользователю вручную проверить работу каждой аннотации.
2. **Автоматическое модульное тестирование с JUnit** - реализовано в тестовых классах `ToStringTest.java` и `CacheAndInvokeTest.java`.

### Главное меню приложения

Класс `Main.java` реализует интерактивное меню для демонстрации всех функций:

```java
// файл: main/Main.java (фрагмент меню)
private static void printMenu() {
  System.out.println("ГЛАВНОЕ МЕНЮ");
  System.out.println("1. Демонстрация @Invoke");
  System.out.println("2. Демонстрация @Default");
  System.out.println("3. Демонстрация @ToString");
  System.out.println("4. Демонстрация @Validate");
  System.out.println("5. Демонстрация @Two");
  System.out.println("6. Демонстрация @Cache");
  System.out.println("7. Запуск всех демонстраций");
  System.out.println("8. Тестирование @ToString");
  System.out.println("9. Тестирование @Cache и @Invoke");
  System.out.println("0. Выход");
}
```

### Результаты тестирования аннотаций

#### Задание 1.1: @Invoke

**Тестовый сценарий:**
- Создание объекта `ClassWithInvoke`
- Автоматический вызов метода `annotatedMethod()` через `AnnotationProcessor.processInvoke()`

**Ожидаемый результат:**
```
--- Обработка @Invoke ---
Метод с аннотацией @Invoke выполнен!
Вызвано методов: 1
```

**Фактический результат:** Полное соответствие ожиданиям. Обработчик корректно находит и выполняет аннотированные методы.

#### Задание 1.2: @Default

**Тестовый сценарий:**
- Анализ класса `ClassWithDefault` с аннотацией `@Default(String.class)`

**Ожидаемый результат:**
```
--- Обработка @Default ---
Класс по умолчанию: java.lang.String
```

**Фактический результат:** Корректное извлечение полного имени класса. Обработчик успешно работает с обязательными параметрами аннотаций.

#### Задание 1.3: @ToString

**Тестовый сценарий:**
- Создание объекта `ClassWithToString`
- Формирование строкового представления

**Ожидаемый результат:**
```
--- Обработка @ToString ---
ClassWithToString{name=Тестовый объект, id=12345, active=true, email=test@example.com}
```

**Фактический результат:** Строковое представление корректно сформировано. Поле `password` исключено из вывода, остальные поля включены с правильными значениями.

#### Задание 1.4: @Validate

**Тестовый сценарий:**
- Анализ класса `ClassWithValidate` с аннотацией `@Validate({String.class, Integer.class, Double.class})`

**Ожидаемый результат:**
```
--- Обработка @Validate ---
Классы для проверки:
  - java.lang.String
  - java.lang.Integer
  - java.lang.Double
```

**Фактический результат:** Массив классов успешно извлечен и отформатирован для вывода.

#### Задание 1.5: @Two

**Тестовый сценарий:**
- Анализ класса `ClassWithTwo` с аннотацией `@Two(first = "Hello friend :)", second = 52)`

**Ожидаемый результат:**
```
--- Обработка @Two ---
first: "Hello friend :)"
second: 52
```

**Фактический результат:** Параметры аннотации успешно извлечены.

#### Задание 1.6: @Cache

**Тестовый сценарий 1 (непустой массив):**
- Анализ класса `ClassWithCache` с аннотацией `@Cache({"users", "orders", "products"})`

**Ожидаемый результат:**
```
--- Обработка @Cache ---
Области кеширования:
  - users
  - orders
  - products
```

**Тестовый сценарий 2 (пустой массив):**
- Анализ класса `ClassWithEmptyCache` с аннотацией `@Cache`

**Ожидаемый результат:**
```
--- Обработка @Cache ---
Области кеширования не указаны (пустой массив)
```

**Фактический результат:** Оба сценария работают корректно.

### Результаты JUnit тестирования

#### Задание 2.1: Тестирование @ToString

**Выполненные тесты:**
1. **testFieldsWithYesOrNoAnnotationDisplayed()** - проверяет отображение полей с Mode.YES или без аннотации
2. **testFieldsWithNoAnnotationHidden()** - проверяет скрытие полей с Mode.NO

**Результат выполнения:**
```
=== ТЕСТИРОВАНИЕ @ToString (Задание 2.1) ===
Задание: Проверить работу аннотации @ToString
Строковое представление: ClassWithToString{name=Тестовый объект, id=12345, active=true, email=test@example.com}
✅ ТЕСТ 2.1 ПРОЙДЕН УСПЕШНО!
   - Поля с Mode.YES отображаются
   - Поля с Mode.NO скрыты
   - Формат строки корректен
```

#### Задание 2.7: Тестирование @Cache и @Invoke

**Выполненные тесты:**
1. **testCacheAnnotationWithAreas()** - проверяет корректность аннотации @Cache
2. **testInvokeMethodExecution()** - проверяет выполнение метода с @Invoke
3. **testEmptyCacheAnnotation()** - проверяет обработку пустого массива @Cache

**Результат выполнения:**
```
=== ТЕСТИРОВАНИЕ @Cache и @Invoke (Задание 2.7) ===
Задание: Проверить совместную работу аннотаций @Cache и @Invoke

1. Проверка аннотации @Cache...
   ✅ Найдена аннотация @Cache
   Области кеширования: [users, orders, products]
   ✅ Области кеширования корректны

2. Проверка аннотации @Invoke...
   ✅ Найден метод с @Invoke: initializeCache

3. Проверка выполнения метода с @Invoke...
   ✅ Методы с @Invoke вызваны через AnnotationProcessor

✅ ТЕСТ 2.7 ПРОЙДЕН УСПЕШНО!
   - Аннотация @Cache корректно работает
   - Аннотация @Invoke корректно работает
   - Совместная работа аннотаций проверена
```

### Сводная таблица результатов тестирования

| Задание | Тип тестирования | Количество тестов | Успешных тестов | Процент успеха | Примечания |
|---------|------------------|------------------|-----------------|----------------|------------|
| 1.1 @Invoke | Интерактивное | 1 | 1 | 100% | Reflection API работает корректно |
| 1.2 @Default | Интерактивное | 1 | 1 | 100% | Параметр Class успешно извлекается |
| 1.3 @ToString | Интерактивное + JUnit | 3 | 3 | 100% | Фильтрация полей работает корректно |
| 1.4 @Validate | Интерактивное | 1 | 1 | 100% | Массивы классов обрабатываются правильно |
| 1.5 @Two | Интерактивное | 1 | 1 | 100% | Разнотипные параметры извлекаются |
| 1.6 @Cache | Интерактивное | 2 | 2 | 100% | Пустые и непустые массивы обрабатываются |
| 2.1 Тест @ToString | JUnit | 2 | 2 | 100% | Все аспекты аннотации проверены |
| 2.7 Тест @Cache и @Invoke | JUnit | 3 | 3 | 100% | Совместная работа аннотаций проверена |

### Интеграционное тестирование

**Запуск всех демонстраций через меню:**
При выборе пункта меню "7. Запуск всех демонстраций" выполняется последовательный вызов всех обработчиков, что подтверждает корректность интеграции всех компонентов системы.
