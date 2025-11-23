package processors;

import annotations.Invoke;
import annotations.Default;
import annotations.ToString;
import annotations.Validate;
import annotations.Two;
import annotations.Cache;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationProcessor {

  // Обработчик для @Invoke
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

  // Обработчик для @Default
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

  // Обработчик для @ToString
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

  // Обработчик для @Validate
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

  // Обработчик для @Two
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

  // Обработчик для @Cache
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
}