package org.dms.utils;


import org.dms.annotations.AutoIncrement;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class ModelHelper {
    private static final AtomicInteger count = new AtomicInteger(0);

    public static void handleAutoIncrement(Object entity) {
        Class<?> entityClass = entity.getClass();
        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoIncrement.class)) {
                field.setAccessible(true);
                try {
                    field.set(entity, count.incrementAndGet());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Auto-incrementing ID failed", e);
                }
            }
        }
    }
}
