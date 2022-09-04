package com.rendavis.nycsatscores.util;

import androidx.annotation.NonNull;

import org.apache.commons.collections4.Transformer;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
    public static <T, R> List<R> mapList(final List<T> list, final Transformer<T, R> func) {
        final List<R> result = new ArrayList<>();
        for (final T entry : list)
            result.add(func.transform(entry));
        return result;
    }

    /**
     * Takes two different type lists and zips them into one if they pass a predicate check.
     *
     * @param first The first list
     * @param second The second list
     * @param predicate Func to test entries within lists
     * @param transform Func to zip entries together
     * @param <T> Type of first list
     * @param <U> Type of second list
     * @param <R> Type of resulting list
     * @return Zipped list
     */
    @NonNull
    public static <T, U, R> List<R> zipLists(
        @NonNull final List<T> first,
        @NonNull final List<U> second,
        @NonNull final BiPredicate<T, U> predicate,
        @NonNull final BiTransform<T, U, R> transform
    ) {
        final List<R> result = new ArrayList<>();
        for (final T ef : first) {
            for (final U es : second) {
                if (predicate.evaluate(ef, es)) {
                    result.add(transform.evaluate(ef, es));
                }
            }
        }
        return result;
    }
}
