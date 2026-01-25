package helpers;

import org.apache.commons.lang3.RandomStringUtils;
import models.User;

public class UserFactory {

    public static User createRandomUser() {
        String name = RandomStringUtils.randomAlphabetic(6);
        String email = RandomStringUtils.randomAlphabetic(6) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphanumeric(8);
        return new User(name, email, password);
    }
}