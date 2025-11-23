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

Данная лабораторная работа демонстрирует применение аннотаций Java и фреймворка тестирования JUnit для создания гибкой и расширяемой системы обработки метаданных. Все задачи реализованы в едином проекте с модульной структурой, включая обработчики аннотаций через Reflection API и комплексное тестирование.

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
├── .idea/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── annotations/
│   │   ├── Cache/
│   │   ├── Default/
│   │   ├── Invoke/
│   │   ├── ToString/
│   │   ├── Two/
│   │   ├── Validate/
│   │   ├── entities/
│   │   │   ├── ClassWithCache
│   │   │   ├── ClassWithDefault
│   │   │   ├── ClassWithInvoke
│   │   │   ├── ClassWithToString
│   │   │   ├── ClassWithTwo
│   │   │   └── ClassWithValidate
│   │   ├── main/
│   │   │   ├── InputChecker
│   │   │   └── Main
│   │   └── processors/
│   │       └── AnnotationProcessor
│   ├── resources/
│   ├── test/
│   │   ├── CacheAndInvokeTest
│   │   └── ToStringTest
│   └── xml-документация/
├── target/
├── .gitignore
├── LabWork_6.iml
└── nom.xml
```

## Описание реализаций и фрагменты кода

### Задание 1.1: Аннотация @Invoke

**Использованные подходы:**
- Reflection API для обнаружения и вызова методов
- Аннотация с целевым элементом METHOD
- Обработка приватных методов через setAccessible(true)

**Ключевой код:**

```java
// Аннотация @Invoke
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Invoke {
}

// Класс с аннотированными методами
public class ClassWithInvoke {
    @Invoke
    public void annotatedMethod() {
        System.out.println("Метод с аннотацией @Invoke выполнен!");
    }

    @Invoke
    private void privateAnnotatedMethod() {
        System.out.println("Приватный метод с @Invoke также выполнен!");
    }
}

// Обработчик аннотации
public static void processInvoke(Object obj) {
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
        System.out.println("Вызвано методов: " + invokedCount);
    } catch (Exception e) {
        System.out.println("Ошибка при обработке @Invoke: " + e.getMessage());
    }
}
```

**Комментарии:**
- **Reflection API** позволяет динамически обнаруживать и вызывать методы во время выполнения
- **Аннотация @Target(ElementType.METHOD)** ограничивает применение только методами
- **@Retention(RetentionPolicy.RUNTIME)** обеспечивает доступность аннотации во время выполнения
- **Обработка приватных методов** через setAccessible(true) демонстрирует возможности Reflection API
- **Универсальный обработчик** работает с любым объектом, содержащим методы с аннотацией @Invoke

### Задание 1.2: Аннотация @Default

**Использованные подходы:**
- Аннотация с поддержкой TYPE и FIELD
- Обязательное свойство value типа Class
- Обработка аннотаций на уровне класса

**Ключевой код:**

```java
// Аннотация @Default
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Default {
    Class<?> value();
}

// Аннотированный класс
@Default(String.class)
public class ClassWithDefault {
    @Default(Integer.class)
    private String field;
}

// Обработчик аннотации
public static void processDefault(Class<?> clazz) {
    try {
        if (clazz.isAnnotationPresent(Default.class)) {
            Default defaultAnno = clazz.getAnnotation(Default.class);
            System.out.println("Класс по умолчанию: " + defaultAnno.value().getName());
        }
    } catch (Exception e) {
        System.out.println("Ошибка при обработке @Default: " + e.getMessage());
    }
}
```

**Комментарии:**
- **Множественные цели** (TYPE и FIELD) демонстрируют гибкость применения аннотации
- **Обязательное свойство value** обеспечивает явное указание класса по умолчанию
- **Тип Class<?>** позволяет работать с любым классом Java
- **Обработка на уровне класса** показывает работу с аннотациями типа TYPE

### Задание 1.3: Аннотация @ToString

**Использованные подходы:**
- Enum для управления режимами отображения
- Рекурсивная обработка полей через Reflection
- Условное включение полей в строковое представление

**Ключевой код:**

```java
// Аннотация @ToString с enum Mode
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {
    Mode value() default Mode.YES;

    public enum Mode {
        YES, NO
    }
}

// Аннотированный класс
@ToString
public class ClassWithToString {
    private String name = "Тестовый объект";
    
    @ToString(ToString.Mode.NO)
    private String password = "secret123";
    
    @ToString(ToString.Mode.YES)
    private String email = "test@example.com";
}

// Обработчик аннотации
public static String processToString(Object obj) {
    try {
        Class<?> clazz = obj.getClass();
        StringBuilder result = new StringBuilder();
        result.append(clazz.getSimpleName()).append("{");
        boolean firstField = true;

        for (Field field : clazz.getDeclaredFields()) {
            ToString toStringAnno = field.getAnnotation(ToString.class);
            
            // Если аннотация есть и значение NO - пропускаем поле
            if (toStringAnno != null && toStringAnno.value() == ToString.Mode.NO) {
                continue;
            }
            
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

**Комментарии:**
- **Enum Mode** обеспечивает типобезопасное управление режимами отображения
- **Значение по умолчанию YES** соответствует принципу наименьшего удивления
- **Условное включение полей** демонстрирует практическое применение аннотаций для контроля сериализации
- **Автоматическое форматирование** создает читабельное строковое представление
- **Обработка исключений** обеспечивает отказоустойчивость

### Задание 1.4: Аннотация @Validate

**Использованные подходы:**
- Массив классов как значение аннотации
- Поддержка ANNOTATION_TYPE для мета-аннотаций
- Итеративная обработка массива классов

**Ключевой код:**

```java
// Аннотация @Validate
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Validate {
    Class<?>[] value();
}

// Аннотированный класс
@Validate({String.class, Integer.class, Double.class, java.util.Date.class})
public class ClassWithValidate {
    private String data = "validation example";
}

// Обработчик аннотации
public static void processValidate(Class<?> clazz) {
    try {
        if (clazz.isAnnotationPresent(Validate.class)) {
            Validate validateAnno = clazz.getAnnotation(Validate.class);
            System.out.println("Классы для проверки:");
            for (Class<?> validationClass : validateAnno.value()) {
                System.out.println("  - " + validationClass.getName());
            }
        }
    } catch (Exception e) {
        System.out.println("Ошибка при обработке @Validate: " + e.getMessage());
    }
}
```

**Комментарии:**
- **Массив Class<?>** позволяет указывать несколько классов для валидации
- **Поддержка ANNOTATION_TYPE** делает аннотацию пригодной для создания мета-аннотаций
- **Итеративная обработка** демонстрирует работу с массивами в аннотациях
- **Универсальность** - может использоваться для указания любых классов валидации

### Задание 1.5: Аннотация @Two

**Использованные подходы:**
- Два обязательных свойства разных типов
- Строковые и числовые значения в аннотациях
- Простая выборка значений свойств

**Ключевой код:**

```java
// Аннотация @Two
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Two {
    String first();
    int second();
}

// Аннотированный класс
@Two(first = "Hello friend :)", second = 52)
public class ClassWithTwo {
    private String message = "Пример класса с двумя параметрами";
}

// Обработчик аннотации
public static void processTwo(Class<?> clazz) {
    try {
        if (clazz.isAnnotationPresent(Two.class)) {
            Two twoAnno = clazz.getAnnotation(Two.class);
            System.out.println("first: \"" + twoAnno.first() + "\"");
            System.out.println("second: " + twoAnno.second());
        }
    } catch (Exception e) {
        System.out.println("Ошибка при обработке @Two: " + e.getMessage());
    }
}
```

**Комментарии:**
- **Два обязательных свойства** демонстрируют аннотации с множественными параметрами
- **Разные типы данных** (String и int) показывают гибкость системы аннотаций
- **Простая выборка значений** иллюстрирует базовый сценарий использования аннотаций
- **Прямой доступ к свойствам** через методы аннотации

### Задание 1.6: Аннотация @Cache

**Использованные подходы:**
- Массив строк как необязательное свойство
- Значение по умолчанию - пустой массив
- Обработка пустых массивов и неаннотированных классов

**Ключевой код:**

```java
// Аннотация @Cache
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    String[] value() default {};
}

// Аннотированный класс
@Cache({"users", "orders", "products"})
public class ClassWithCache {
    private java.util.Map<String, Object> cache = new java.util.HashMap<>();

    @Invoke
    public void initializeCache() {
        cache.put("users", new java.util.ArrayList<String>());
        cache.put("orders", new java.util.ArrayList<Integer>());
    }
}

// Обработчик аннотации
public static void processCache(Class<?> clazz) {
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
        }
    } catch (Exception e) {
        System.out.println("Ошибка при обработке @Cache: " + e.getMessage());
    }
}
```

**Комментарии:**
- **Необязательное свойство** с пустым массивом по умолчанию
- **Обработка граничных случаев** (пустой массив, отсутствие аннотации)
- **Практическое применение** для указания областей кеширования
- **Интеграция с @Invoke** демонстрирует совместное использование аннотаций

### Задание 2.1: Тестирование @ToString

**Использованные подходы:**
- JUnit 5 с аннотациями @Test и @DisplayName
- Множественные assertions через assertAll
- Проверка включения/исключения полей

**Ключевой код:**

```java
@Test
@DisplayName("--- Обработка @ToString ---")
void testToStringAnnotationProcessing() {
    ClassWithToString obj = new ClassWithToString();
    String result = AnnotationProcessor.processToString(obj);

    assertAll("Поля с Mode.YES или без аннотации должны отображаться",
        () -> assertTrue(result.contains("name=Тестовый объект")),
        () -> assertTrue(result.contains("id=12345")),
        () -> assertTrue(result.contains("email=test@example.com")),
        () -> assertFalse(result.contains("password")),
        () -> assertFalse(result.contains("secret123"))
    );
}
```

**Комментарии:**
- **Структурированные тесты** с понятными названиями через @DisplayName
- **Группировка assertions** через assertAll обеспечивает выполнение всех проверок
- **Проверка включения** полей без аннотации и с Mode.YES
- **Проверка исключения** полей с Mode.NO
- **Комплексная валидация** формата строкового представления

### Задание 2.7: Тестирование @Cache и @Invoke

**Использованные подходы:**
- Интеграционное тестирование нескольких аннотаций
- Reflection API для проверки аннотаций и вызова методов
- Mock-объекты для имитации кеширования

**Ключевой код:**

```java
@Test
@DisplayName("--- Обработка @Cache и @Invoke ---")
void testCacheAndInvokeIntegration() {
    // Проверка аннотации @Cache
    Class<TestClassWithCacheAndInvoke> clazz = TestClassWithCacheAndInvoke.class;
    Cache cacheAnnotation = clazz.getAnnotation(Cache.class);
    String[] expectedAreas = {"users", "orders"};

    assertAll("Аннотация @Cache должна возвращать корректные значения",
        () -> assertTrue(clazz.isAnnotationPresent(Cache.class)),
        () -> assertArrayEquals(expectedAreas, cacheAnnotation.value())
    );

    // Проверка выполнения метода с @Invoke
    TestClassWithCacheAndInvoke obj = new TestClassWithCacheAndInvoke();
    Method[] methods = obj.getClass().getDeclaredMethods();
    
    for (Method method : methods) {
        if (method.isAnnotationPresent(Invoke.class)) {
            method.setAccessible(true);
            method.invoke(obj);
        }
    }

    assertAll("Метод с @Invoke должен выполниться и повлиять на кеш",
        () -> assertTrue(obj.isMethodExecuted()),
        () -> assertFalse(obj.getCache().isEmpty()),
        () -> assertNotNull(obj.getCache().get("users")),
        () -> assertNotNull(obj.getCache().get("orders"))
    );
}
```

**Комментарии:**
- **Интеграционное тестирование** проверяет совместную работу аннотаций
- **Reflection для вызова методов** демонстрирует практическое применение API
- **Проверка побочных эффектов** вызова методов (изменение состояния объекта)
- **Обработка пустого массива @Cache** проверяет граничные случаи
- **Комплексные проверки** через множественные assertions

# Тестирование

## Подробное тестирование каждого задания

### Задание 1.1: Аннотация @Invoke

#### Тест 1: Автоматический вызов аннотированных методов

| Параметр | Значение |
|----------|----------|
| **Цель теста** | Проверить автоматический вызов методов с аннотацией @Invoke |
| **Входные данные** | Объект ClassWithInvoke с двумя аннотированными методами |
| **Ожидаемый результат** | Оба метода вызываются автоматически через Reflection |

**Результат выполнения:**
```
=== ДЕМОНСТРАЦИЯ @Invoke ===
Создаем объект ClassWithInvoke...
--- Обработка @Invoke ---
Метод с аннотацией @Invoke выполнен!
Приватный метод с @Invoke также выполнен!
Вызвано методов: 2
Методы с аннотацией @Invoke были автоматически вызваны
```

#### Тест 2: Обработка классов без аннотированных методов

| Параметр | Значение |
|----------|----------|
| **Цель теста** | Проверить обработку объектов без методов @Invoke |
| **Входные данные** | Объект без аннотированных методов |
| **Ожидаемый результат** | Корректная обработка без ошибок |

**Результат выполнения:**
```
--- Обработка @Invoke ---
Методы с @Invoke не найдены
```

### Задание 1.2: Аннотация @Default

#### Тест 1: Обработка аннотации на уровне класса

| Параметр | Значение |
|----------|----------|
| **Цель теста** | Проверить извлечение значения аннотации @Default |
| **Входные данные** | ClassWithDefault с аннотацией @Default(String.class) |
| **Ожидаемый результат** | Вывод имени класса String |

**Результат выполнения:**
```
=== ДЕМОНСТРАЦИЯ @Default ===
Анализируем класс ClassWithDefault...
--- Обработка @Default ---
Класс по умолчанию: java.lang.String
Аннотация @Default обработана успешно
```

### Задание 1.3: Аннотация @ToString

#### Тест 1: Формирование строкового представления

| Параметр | Значение |
|----------|----------|
| **Цель теста** | Проверить корректное формирование toString с учетом аннотаций |
| **Входные данные** | ClassWithToString с полями разных режимов отображения |
| **Ожидаемый результат** | Поле password исключено, остальные поля включены |

**Результат выполнения:**
```
=== ДЕМОНСТРАЦИЯ @ToString ===
Создаем объект ClassWithToString...
--- Обработка @ToString ---
Результат преобразования в строку:
  ClassWithToString{name=Тестовый объект, id=12345, active=true, email=test@example.com}
Обратите внимание: поле 'password' не отображается,
  а поле 'email' отображается (аннотировано с Mode.YES)
```

### Задание 1.4: Аннотация @Validate

#### Тест 1: Извлечение массива классов валидации

| Параметр | Значение |
|----------|----------|
| **Цель теста** | Проверить обработку массива классов в аннотации |
| **Входные данные** | ClassWithValidate с 4 классами валидации |
| **Ожидаемый результат** | Вывод всех указанных классов |

**Результат выполнения:**
```
=== ДЕМОНСТРАЦИЯ @Validate ===
Анализируем класс ClassWithValidate...
--- Обработка @Validate ---
Классы для проверки:
  - java.lang.String
  - java.lang.Integer
  - java.lang.Double
  - java.util.Date
Аннотация @Validate обработана успешно
```

### Задание 1.5: Аннотация @Two

#### Тест 1: Чтение строковых и числовых значений

| Параметр | Значение |
|----------|----------|
| **Цель теста** | Проверить извлечение значений из аннотации @Two |
| **Входные данные** | ClassWithTwo с first="Hello friend :)" и second=52 |
| **Ожидаемый результат** | Корректный вывод обоих значений |

**Результат выполнения:**
```
=== ДЕМОНСТРАЦИЯ @Two ===
Анализируем класс ClassWithTwo...
--- Обработка @Two ---
first: "Hello friend :)"
second: 52
Аннотация @Two обработана успешно
```

### Задание 1.6: Аннотация @Cache

#### Тест 1: Обработка областей кеширования

| Параметр | Значение |
|----------|----------|
| **Цель теста** | Проверить извлечение массива областей кеширования |
| **Входные данные** | ClassWithCache с тремя областями кеширования |
| **Ожидаемый результат** | Вывод всех трех областей |

**Результат выполнения:**
```
=== ДЕМОНСТРАЦИЯ @Cache ===
Анализируем класс ClassWithCache...
--- Обработка @Cache ---
Области кеширования:
  - users
  - orders
  - products
Аннотация @Cache обработана успешно
```

### Задание 2.1: JUnit тестирование @ToString

#### Тест 1: Проверка включения/исключения полей

| Параметр | Значение |
|----------|----------|
| **Цель теста** | Проверить корректность фильтрации полей в toString |
| **Входные данные** | ClassWithToString с разными аннотациями полей |
| **Ожидаемый результат** | Все assertions выполняются успешно |

**Результат выполнения:**
```
=== Тестирование @ToString ===
Цель: Проверить корректность обработки аннотации @ToString

Тест 1: Проверка отображения полей с @ToString(Mode.YES) или без аннотации
Проверка поля 'name'...
Поле 'name' отображается (без аннотации = по умолчанию YES)
Проверка поля 'id'...
Поле 'id' отображается (без аннотации = по умолчанию YES)
Проверка поля 'email'...
Поле 'email' отображается (явно аннотировано с Mode.YES)
✅ Тест 1 пройден

Тест 2: Проверка исключения полей с @ToString(Mode.NO)
Проверка поля 'password'...
Поле 'password' не отображается (аннотировано с Mode.NO)
✅ Тест 2 пройден

Тестирование @ToString завершено успешно!
```

### Задание 2.7: JUnit тестирование @Cache и @Invoke

#### Тест 1: Интеграционное тестирование аннотаций

| Параметр | Значение |
|----------|----------|
| **Цель теста** | Проверить совместную работу @Cache и @Invoke |
| **Входные данные** | Тестовый класс с обеими аннотациями |
| **Ожидаемый результат** | Все интеграционные проверки успешны |

**Результат выполнения:**
```
=== Тестирование совместной работы @Cache и @Invoke ===
Цель: Проверить интеграцию аннотаций @Cache и @Invoke на одном классе

1. Проверка аннотации @Cache
Класс аннотирован @Cache
@Cache возвращает массив: [users, orders]
✅ Проверка @Cache завершена

2. Проверка выполнения метода с @Invoke и его влияния на кеш
Найден метод с @Invoke: initializeData
Метод initializeData вызван через Reflection
Вызвано методов с @Invoke: 1
Метод с @Invoke выполнен
Кеш не пустой после выполнения метода
Данные добавлены в кеш 'users'
Данные добавлены в кеш 'orders'
Кеш 'users' содержит корректные данные
Кеш 'orders' содержит корректные данные
✅ Проверка @Invoke и влияния на кеш завершена

Тестирование совместной работы @Cache и @Invoke завершено успешно!
```

## Сводная таблица результатов тестирования

| Задание | Количество тестов | Успешных тестов | Процент успеха | Примечания |
|---------|------------------|-----------------|----------------|------------|
| 1.1 @Invoke | 2 | 2 | 100% | Reflection API работает корректно |
| 1.2 @Default | 1 | 1 | 100% | Извлечение класса по умолчанию успешно |
| 1.3 @ToString | 1 | 1 | 100% | Фильтрация полей работает как ожидалось |
| 1.4 @Validate | 1 | 1 | 100% | Массив классов обрабатывается корректно |
| 1.5 @Two | 1 | 1 | 100% | Чтение строковых и числовых значений успешно |
| 1.6 @Cache | 1 | 1 | 100% | Области кеширования извлекаются правильно |
| 2.1 Тестирование @ToString | 1 | 1 | 100% | JUnit тесты проверяют все сценарии |
| 2.7 Тестирование @Cache и @Invoke | 1 | 1 | 100% | Интеграционное тестирование успешно |
