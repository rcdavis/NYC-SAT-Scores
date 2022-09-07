package com.rendavis.nycsatscores.util;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.BiPredicate;
import io.reactivex.rxjava3.functions.Function;

public class CollectionUtils {
    public static <T, R> List<R> mapList(
        final List<T> list, final Function<T, R> func) throws Throwable
    {
        final List<R> result = new ArrayList<>();
        for (final T entry : list)
            result.add(func.apply(entry));
        return result;
    }

    /**
     * Takes two different type lists and zips them into one if they pass a predicate check.
     *
     * @param first The first list
     * @param second The second list
     * @param predicate Func to test entries within lists
     * @param transform Func to zip entries together
     * @param <T1> Type of first list
     * @param <T2> Type of second list
     * @param <R> Type of resulting list
     * @return Zipped list
     */
    @NonNull
    public static <T1, T2, R> List<R> zipLists(
        @NonNull final List<T1> first,
        @NonNull final List<T2> second,
        @NonNull final BiPredicate<T1, T2> predicate,
        @NonNull final BiFunction<T1, T2, R> transform
    ) throws Throwable {
        final List<R> result = new ArrayList<>();
        for (final T1 e1 : first) {
            for (final T2 e2 : second) {
                if (predicate.test(e1, e2)) {
                    result.add(transform.apply(e1, e2));
                }
            }
        }
        return result;
    }
}
