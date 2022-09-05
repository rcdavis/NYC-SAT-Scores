package com.rendavis.nycsatscores.util;

@FunctionalInterface
public interface BiTransform<T, U, R> {
    R evaluate(final T first, final U second);
}
