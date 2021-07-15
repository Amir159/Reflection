package com.syncretis;

public class House {
    @FieldName("House number")
    private int id;
    private String location;

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", location='" + location + '\'' +
                '}';
    }
}
