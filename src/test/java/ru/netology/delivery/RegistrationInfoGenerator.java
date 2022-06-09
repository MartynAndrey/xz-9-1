package ru.netology.delivery;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class RegistrationInfoGenerator {
    private static int minDeviation = 3;
    private static int maxDeviation = 30;
    private static String locale = "ru";

    public static String generateCity() {
        Faker faker = new Faker(new Locale(locale));
        return faker.address().city();
    }

    public static String generateDate() {
        Faker faker = new Faker(new Locale(locale));
        Date date = faker.date().future(maxDeviation, minDeviation, TimeUnit.DAYS);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().name();
    }

    public static String generatePhone() {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static RegistrationInfo generateRegistrationInfo() {
        return new RegistrationInfo(generateCity(), generateName(), generatePhone());
    }
}
