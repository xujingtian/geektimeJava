package com.example.db;

public class DSHolder {
    private static ThreadLocal<String> container = new ThreadLocal<>();

    public static void setDS(String ds) {
        container.set(ds);
    }

    public static String getDS() {
        return container.get();
    }

    public static void clear() {
        container.remove();
    }
}
