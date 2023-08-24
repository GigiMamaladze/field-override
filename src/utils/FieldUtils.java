package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.AbstractPage;
import domain.AbstractUIObject;
import domain.ExtendedWebElement;
import domain.Parent;
import domain.WebElement;

public class FieldUtils {

    private static final List<String> BREAK_POINTS = List.of(
            Object.class.getName(),
            AbstractUIObject.class.getName(),
            AbstractPage.class.getName()
    );

    private static final List<Class<?>> ALLOWED_FIELD_CLASSES = List.of(
            Parent.class,
            String.class,

            WebElement.class,
            ExtendedWebElement.class,
            AbstractUIObject.class,
            AbstractPage.class
    );

    public static <T> void overrideFields(T instance) {
        Map<FieldIdentifier, Object> fieldValues = new HashMap<>();

        Class<?> currentClass = instance.getClass();
        while (currentClass != null && !BREAK_POINTS.contains(currentClass.getName())) {
            Field[] fields = currentClass.getDeclaredFields();
            for (Field field : fields) {
                overrideField(instance, field, fieldValues);
            }

            currentClass = currentClass.getSuperclass();
        }
    }

    private static <T> void overrideField(T instance, Field field, Map<FieldIdentifier, Object> existingFieldValues) {
        boolean hasAcceptableType = hasAcceptableType(field);
        boolean canBeOverridden = canBeOverridden(field);
        if (hasAcceptableType && canBeOverridden) {
            String fieldName = field.getName();
            FieldIdentifier fieldId = new FieldIdentifier(fieldName, field.getType().getName());
            Object existingValue = existingFieldValues.get(fieldId);
            if (existingValue != null) {
                setFieldValue(field, instance, existingValue);
            } else {
                Object currentFieldValue = getFieldValue(field, instance);
                existingFieldValues.putIfAbsent(fieldId, currentFieldValue);
            }
        }
    }

    private static boolean canBeOverridden(Field field) {
        return !Modifier.isFinal(field.getModifiers()) && !Modifier.isStatic(field.getModifiers());
    }

    private static boolean hasAcceptableType(Field field) {
        return ALLOWED_FIELD_CLASSES.stream()
                .anyMatch(allowedClass -> allowedClass.isAssignableFrom(field.getType()));
    }

    private static Object getFieldValue(Field field, Object instance) {
        try {
            field.setAccessible(true);
            return field.get(instance);
        } catch (IllegalAccessException e) {
            String fieldName = field.getName();
            String message = String.format("Unable to get '%s' field value. ", fieldName);
            throw new RuntimeException(message + e.getMessage(), e);
        }
    }

    private static void setFieldValue(Field field, Object instance, Object value) {
        try {
            field.setAccessible(true);
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            String fieldName = field.getName();
            String message = String.format("Unable to set a value into the '%s' field. ", fieldName);
            throw new RuntimeException(message + e.getMessage(), e);
        }
    }
}
