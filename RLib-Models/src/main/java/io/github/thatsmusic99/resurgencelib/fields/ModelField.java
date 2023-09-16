package io.github.thatsmusic99.resurgencelib.fields;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A model field to be saved into a database table.
 * <p>
 * The following data types can be stored in tables without issue:
 * - Bytes
 * - Shorts
 * - Ints
 * - Longs
 * - Floats
 * - Doubles
 * - Strings
 * - UUIDs
 * - Enums
 * <p>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ModelField {

    boolean primary() default false;

    boolean indexed() default false;

    int length() default 64;

    boolean fixed() default false;

    boolean unsigned() default false;
}
