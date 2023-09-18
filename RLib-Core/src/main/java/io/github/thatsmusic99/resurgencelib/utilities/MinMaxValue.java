package io.github.thatsmusic99.resurgencelib.utilities;

import org.jetbrains.annotations.NotNull;

/**
 * A generic class representing a pair of minimum and maximum values of a comparable type.
 * @param <T> The type of values stored in the MinMaxValue object, which must be Comparable.
 */
public class MinMaxValue<T extends Number & Comparable<T>> {
    // Store the minimum and maximum values.
    private final T minimum;
    private final T maximum;

    /**
     * Constructor to create a MinMaxValue object from two values.
     *
     * @param a The first value.
     * @param b The second value.
     */
    public MinMaxValue(@NotNull T a, @NotNull T b) {
        // Determine the minimum and maximum values from the input values.
        this.minimum = min(a, b);
        this.maximum = max(a, b);
    }

    /**
     * Get the maximum value of the pair.
     *
     * @return The maximum value.
     */
    public T maximum() {
        return maximum;
    }

    /**
     * Get the minimum value of the pair.
     *
     * @return The minimum value.
     */
    public T minimum() {
        return minimum;
    }

    /**
     * Determine the minimum of two values.
     *
     * @param a The first value.
     * @param b The second value.
     * @return The minimum of the two values.
     */
    private T min(T a, T b) {
        // Return the smaller of the two values.
        return (a.compareTo(b) > 0) ? b : a;
    }

    /**
     * Determine the maximum of two values.
     *
     * @param a The first value.
     * @param b The second value.
     * @return The maximum of the two values.
     */
    private T max(T a, T b) {
        // Return the larger of the two values.
        return (a.compareTo(b) > 0) ? a : b;
    }
}
