package com.rendavis.nycsatscores.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
    public static <T, R> List<R> mapList(final List<T> list, final MapFunction<T, R> func) {
        final List<R> result = new ArrayList<>();
        for (final T entry : list)
            result.add(func.apply(entry));
        return result;
    }
}
