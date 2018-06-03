package com.spothire.shabab477.simplevalidation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is used to constraint a specific size. Support for only {@link String} and objects that
 * are part of the {@link java.util.Collection} interface. For {@link String} the length is called for reference
 * and for {@link java.util.Collection} the size is taken into consideration.
 * <p>
 * <br />
 *
 * <p color="red">
 * Warning: This does not check for nulls. For null checks look use {@link NotNull}
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Size {

    String message() default "";

    int size() default Integer.MAX_VALUE;

    int min() default Integer.MAX_VALUE;

    int max() default Integer.MIN_VALUE;
}
