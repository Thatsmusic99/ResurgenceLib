package io.github.thatsmusic99.resurgencelib.utilities;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

public class DataConverter {

    private static final @NotNull HashMap<Class<?>, Convert> CONVERTER;

    static {
        CONVERTER = new HashMap<>();

        CONVERTER.put(UUID.class, new Convert(Object::toString, uuid -> UUID.fromString((String) uuid)));
    }

    @Contract("null -> null")
    public static Object convertToSafe(@Nullable Object obj) {
        if (obj == null) return null;
        if (!CONVERTER.containsKey(obj.getClass())) return obj;
        return CONVERTER.get(obj.getClass()).toSafe.apply(obj);
    }

    @Contract("null -> null")
    public static Object convertFromSafe(@Nullable Object obj) {
        if (obj == null) return null;
        if (!CONVERTER.containsKey(obj.getClass())) return obj;
        return CONVERTER.get(obj.getClass()).fromSafe.apply(obj);
    }

    private record Convert(Function<Object, Object> toSafe, Function<Object, Object> fromSafe) {}
}
