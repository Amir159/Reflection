package com.syncretis;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Parser parser = new Parser();

        Person person1 = new Person();

        Field name = Person.class.getDeclaredField("name");
        Field phoneNumber = Person.class.getDeclaredField("phoneNumber");
        name.setAccessible(true);
        phoneNumber.setAccessible(true);
        name.set(person1, "Sergey");
        phoneNumber.set(person1, 89138715212L);

        System.out.println(parser.serialize(person1));


        String jsonSrtPerson = "{\n\t\"Nickname\": \"Azamat\",\n\t\"Phone number\": 89994569876\n}";
        Person person2 = (Person) parser.deserialize(jsonSrtPerson, Person.class);

        System.out.println(person2);

        House house1 = new House();

        Field idHouse = House.class.getDeclaredField("id");
        Field location = House.class.getDeclaredField("location");
        idHouse.setAccessible(true);
        location.setAccessible(true);
        idHouse.set(house1, 246);
        location.set(house1, "Lenina");

        System.out.println(parser.serialize(house1));

        String jsonSrtHouse = "{\n\t\"House number\": 159,\n\t\"location\": \"Vershinina\"\n}";
        House house2 = (House) parser.deserialize(jsonSrtHouse, House.class);

        System.out.println(house2);

        Auto auto1 = new Auto();

        Field color = Auto.class.getDeclaredField("color");
        Field idAuto = Auto.class.getDeclaredField("id");
        color.setAccessible(true);
        idAuto.setAccessible(true);
        color.set(auto1, "Sky Blue");
        idAuto.set(auto1, "603KEK70");

        System.out.println(parser.serialize(auto1));

        String jsonSrtAuto = "{\n\t\"color\": \"Red\",\n\t\"id\": \"321EOE70\"\n}";
        Auto auto2 = (Auto) parser.deserialize(jsonSrtAuto, Auto.class);

        System.out.println(auto2);
    }
}
