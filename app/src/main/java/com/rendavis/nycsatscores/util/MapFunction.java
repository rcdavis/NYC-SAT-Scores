package com.rendavis.nycsatscores.util;

public interface MapFunction<T, R> {
    R apply(final T type);
}
