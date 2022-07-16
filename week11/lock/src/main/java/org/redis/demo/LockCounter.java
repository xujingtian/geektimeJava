package org.redis.demo;

import java.util.Map;

public class LockCounter {
    public static ThreadLocal<Map<String, Integer>> counter = new ThreadLocal<>();

    public static Integer getLockCount(String key) {
        return counter.get().get(key);
    }

    public static void plusOne(String key) {
        if(counter.get() == null) {
            counter.get().put(key, 1);
        } else {
            counter.get().put(key,counter.get().get(key) + 1);
        }
    }

    public static void minusOne(String key) {
        counter.get().put(key, counter.get().get(key) - 1);
    }
}
