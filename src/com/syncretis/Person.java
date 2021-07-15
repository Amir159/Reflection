package com.syncretis;

public class Person {
    @FieldName("Nickname")
    private String name;
    @FieldName("Phone number")
    private long phoneNumber;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
