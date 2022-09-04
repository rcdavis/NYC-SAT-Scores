package com.rendavis.nycsatscores.util;

import org.apache.commons.lang3.function.FailableFunction;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
    public static <T, R, E extends Throwable> List<R> mapList(
        final List<T> list,
        final FailableFunction<T, R, E> func
    ) {
        final List<R> result = new ArrayList<>();
        for (final T entry : list) {
            try {
                result.add(func.apply(entry));
            } catch (final Throwable e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
