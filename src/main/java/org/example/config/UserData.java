package org.example.config;

import java.util.UUID;

public class UserData {

    // Генерация уникальных тестовых данных
    public static String generateUniqueEmail() {
        return "testuser_" + UUID.randomUUID().toString().substring(0, 8) + "@yandex.ru";
    }

    public static String generateUniqueName() {
        return "TestUser_" + UUID.randomUUID().toString().substring(0, 6);
    }

    public static final String DEFAULT_PASSWORD = "password123";
}