package data;

import java.util.UUID;

public class TestData {

    public static String getRandomEmail() {
        return "test_" + UUID.randomUUID() + "@mail.com";
    }

    public static String getRandomName() {
        return "User_" + UUID.randomUUID().toString().substring(0, 5);
    }

    public static String getRandomPassword() {
        return "Pwd" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String shortPassword = "123";
    public static String existingEmail = "existing_user@mail.com";
}
