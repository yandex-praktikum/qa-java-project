package praktikum.api.util;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.Arrays;

public class TestData {

    public static String uniqueEmail() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8).toLowerCase(Locale.ROOT) + "@example.com";
    }

    public static String strongPassword() {
        return "Pass!" + UUID.randomUUID().toString().substring(0, 8);
    }

    public static String randomName() {
        return "User_" + UUID.randomUUID().toString().substring(0, 4);
    }

    // Динамический список реальных ингредиентов
    public static List<String> realIngredients() {
        return Arrays.asList(
                "61c0c5a71d1f82001bdaaa71",
                "61c0c5a71d1f82001bdaaa76",
                "61c0c5a71d1f82001bdaaa77",
                "61c0c5a71d1f82001bdaaa78",
                "61c0c5a71d1f82001bdaaa79"
        );
    }
}
