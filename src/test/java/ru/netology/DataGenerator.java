package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(long addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generateCity() {
        String[] cities = new String[]{"Барнаул", "Владикавказ", "Казань", "Калининград", "Калуга",
                "Краснодар", "Хабаровск", "Ханты-Мансийск",  "Великий Новгород", "Владивосток", "Владимир",
                "Вологда", "Рязань", "Биробиджан", "Чебоксары", "Москва", "Санкт-Петербург", "Ульяновск",
                "Симферополь", "Ростов-на-Дону"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            return new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}