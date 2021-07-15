package com.syncretis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public StringBuilder serialize(Object obj) throws IllegalAccessException {
        StringBuilder finishedStr = new StringBuilder("{\n");
        Field[] fields = obj.getClass().getDeclaredFields();
        int count = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().equals(String.class)) {
                if (field.isAnnotationPresent(FieldName.class)) {
                    finishedStr.append("\t\"").append(field.getAnnotation(FieldName.class).value()).append("\": \"").append(field.get(obj)).append("\"");
                }
                else {
                    finishedStr.append("\t\"").append(field.getName()).append("\": \"").append(field.get(obj)).append("\"");
                }
            } else if (field.getType().equals(long.class)) {
                if (field.isAnnotationPresent(FieldName.class)) {
                    finishedStr.append("\t\"").append(field.getAnnotation(FieldName.class).value()).append("\": ").append(field.get(obj));
                } else {
                    finishedStr.append("\t\"").append(field.getName()).append("\": ").append(field.get(obj));
                }
            }  else if (field.getType().equals(int.class)) {
                if (field.isAnnotationPresent(FieldName.class)) {
                    finishedStr.append("\t\"").append(field.getAnnotation(FieldName.class).value()).append("\": ").append(field.get(obj));
                } else {
                    finishedStr.append("\t\"").append(field.getName()).append("\": ").append(field.get(obj));
                }
            }
            count++;
            if (count < fields.length) {
                finishedStr.append(",\n");
            } else {
                finishedStr.append('\n');
            }

        }
        finishedStr.append("}");
        return finishedStr;
    }

    public <T> T deserialize(String str, Class<T> obj) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Constructor constructor = obj.getConstructor();
        T obj12 = (T) constructor.newInstance();
        Field[] fields = obj12.getClass().getDeclaredFields();
        str = str.replaceAll("[{}\t\"\n]", "");
        String[] parsedStr = str.split(",");
        HashMap<String, String> strMap = new HashMap<>();

        for (String s : parsedStr) {
            String[] pairKeyValue = s.split(": ");
            strMap.put(pairKeyValue[0], pairKeyValue[1]);
        }

        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(FieldName.class)) {
                    field.set(obj12, strMap.get(field.getAnnotation(FieldName.class).value()));
                } else {
                    field.set(obj12, strMap.get(field.getName()));
                }
            }
            else if (field.getType().equals(long.class)) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(FieldName.class)) {
                    field.set(obj12, Long.parseLong(strMap.get(field.getAnnotation(FieldName.class).value())));
                } else {
                    field.set(obj12, strMap.get(field.getName()));
                }
            }
            else if (field.getType().equals(int.class)) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(FieldName.class)) {
                    field.set(obj12, Integer.parseInt(strMap.get(field.getAnnotation(FieldName.class).value())));
                } else {
                    field.set(obj12, strMap.get(field.getName()));
                }
            }
        }

        return obj12;
    }
}
