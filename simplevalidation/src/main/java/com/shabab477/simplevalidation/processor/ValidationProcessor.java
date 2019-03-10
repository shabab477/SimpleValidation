package com.shabab477.simplevalidation.processor;

import android.util.Log;

import com.shabab477.simplevalidation.annotation.Email;
import com.shabab477.simplevalidation.annotation.Future;
import com.shabab477.simplevalidation.annotation.NotNull;
import com.shabab477.simplevalidation.annotation.Size;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * This class is the engine that produces the Map which has the key value pairs of the errors. The
 * static method validate is to be called.
 */
public abstract class ValidationProcessor {

    private static final Predicate<FieldHolder> fieldSizePredicate = fieldHolder -> {

        Size size = fieldHolder.getField().getAnnotation(Size.class);

        int min = size.min();
        int max = size.max();
        int sz = size.size();
        fieldHolder.getField().setAccessible(true);

        try {

            if (Collection.class.isAssignableFrom(fieldHolder.getField().getType())) {
                int length = ((Collection) fieldHolder.getField().get(fieldHolder.getObject())).size();

                if (length >= min && length <= max) {

                    if (sz == Integer.MAX_VALUE) {
                        return true;
                    } else {
                        return sz == length;
                    }
                }

            } else if (String.class.isAssignableFrom(fieldHolder.getField().getType())) {
                int length = ((String) fieldHolder.getField().get(fieldHolder.getObject())).length();

                if (length >= min && length <= max) {
                    if (sz == Integer.MAX_VALUE) {
                        return true;
                    } else {
                        return sz == length;
                    }
                }
            }
        } catch (IllegalAccessException | NullPointerException ex) {
//            ex.printStackTrace();
            return false;
        } finally {
            fieldHolder.getField().setAccessible(false);
        }

        return false;
    };

    private static final Predicate<FieldHolder> nullPredicate = fieldHolder -> {
        fieldHolder.getField().setAccessible(true);

        try {
            return fieldHolder.getField().get(fieldHolder.getObject()) != null;
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } finally {
            fieldHolder.getField().setAccessible(false);
        }

        return false;
    };

    private static final Predicate<FieldHolder> dateFuturePredicate = fieldHolder -> {
        java.util.Date dateOfBirth = null;
        fieldHolder.getField().setAccessible(true);

        if (fieldHolder.getField().getType().equals(java.util.Date.class)) {
            try {
               dateOfBirth = java.util.Date.class.cast(fieldHolder.getField().get(fieldHolder.getObject()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            } finally {
                fieldHolder.getField().setAccessible(false);
            }
        } else {
            return false;
        }

        java.util.Date dt = new java.util.Date();

        return dateOfBirth.after(dt);
    };

    private static final Predicate<FieldHolder> emailPredicate = fieldHolder -> {
        fieldHolder.getField().setAccessible(true);
        try {
            String email = fieldHolder.getField().get(fieldHolder.getObject()).toString();

            if (email == null) {
                return false;
            } else {
                Pattern compile = Pattern.compile(
                        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                                "\\@" +
                                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                                "(" +
                                "\\." +
                                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                                ")+"
                );

                return compile.matcher(email).matches();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } finally {
            fieldHolder.getField().setAccessible(false);
        }
    };

    private static String processMessage(String message, int min, int max) {
        if (min == Integer.MAX_VALUE && max == Integer.MIN_VALUE) {
            return message == null? "Field is not valid" : message;
        } else if (message != null) {
            return message
                        .replaceAll("\\$\\{max\\}", String.valueOf(max))
                        .replaceAll("\\$\\{min\\}", String.valueOf(min));
        } else {
            return "Field is not valid";
        }
    }

    /**
     * This is the utility method that produces the @link java.util.Map of the key value pairs of the errors.
     *
     * @param object The object to be validated.
     * @return A {@link Map} which has the key value pairs of the errors. The field names in {@link String} will be the key of the error in the Map and the value will be {@link String} error message
     */
    public static HashMap<String, String> validate(Object object) {
        return validateProxy(object, object.getClass(), new HashMap<>());
    }

    private static HashMap<String, String> validateProxy(Object object, Class clazz, HashMap<String, String> map) {

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            FieldHolder holder = new FieldHolder(object, field);
            boolean hasError = false;

            if (field.isAnnotationPresent(Size.class)) {
                if (!fieldSizePredicate.test(holder)) {
                    hasError = true;
                    Size annotation = field.getAnnotation(Size.class);
                    int max = annotation.max();
                    int min = annotation.min();

                    String message = annotation.message();

                    if (message.length() == 0) {

                        map.put(field.getName(), "Field is not valid");
                    } else {

                        map.put(field.getName(), processMessage(message, min, max));
                    }
                }
            }

            if (field.isAnnotationPresent(NotNull.class) && !hasError) {
                if (!nullPredicate.test(holder)) {
                    NotNull annotation = field.getAnnotation(NotNull.class);
                    String message = annotation.message();

                    if (message.length() == 0) {

                        map.put(field.getName(), "Field is not valid");
                    } else {

                        map.put(field.getName(), message);
                    }
                }
            }

            if (field.isAnnotationPresent(Future.class) && !hasError) {
                if (!dateFuturePredicate.test(holder)) {
                    map.put(field.getName(), "Date must be in the future");
                }
            }

            if (field.isAnnotationPresent(Email.class) && !hasError) {
                if (!emailPredicate.test(holder)) {
                  map.put(field.getName(), "Not a valid email");
                }
            }
        }

        if (clazz.getSuperclass() != null && !Modifier.isAbstract(clazz.getSuperclass().getModifiers())) {
            return validateProxy(object, clazz.getSuperclass(), map);
        }

        return map;
    }

    private static class FieldHolder {

        private Object object;

        private Field field;

        FieldHolder(Object object, Field field) {
            this.object = object;
            this.field = field;
        }

        Object getObject() {
            return object;
        }

        void setObject(Object object) {
            this.object = object;
        }

        Field getField() {
            return field;
        }

        void setField(Field field) {
            this.field = field;
        }
    }

    /**
     * Because Java 8 functional interfaces requires API Level 24 to function
     *
     * @param <T> Any object to test against
     */
    @FunctionalInterface
    private interface Predicate<T> {

        boolean test(T object);
    }
}
