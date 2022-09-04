package com.rendavis.nycsatscores.util;

@FunctionalInterface
public interface BiPredicate<T, U> {
    boolean evaluate(final T first, final U second);
}
