package praktikum.utill;
import io.qameta.allure.Step;
import net.datafaker.Faker;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import praktikum.models.User;

import java.util.Locale;

public class TestDataGenerator {

    @Contract(" -> new")
    private static @NotNull Faker faker() {
        return new Faker(new Locale("ru"));
    }
    @Step("Генерация случайного пользователя (email, password, name)")
    public static @NotNull User randomUser() {
        Faker f = faker();
        String name = f.name().fullName();
        String email = f.internet().emailAddress();
        String password = f.internet().password(8, 16, true, true, true);
        return new User(email, password, name);
    }
}
