package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import io.qameta.allure.Description;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @DisplayName("Получение названия булочки")
    @Description("Проверяем, что метод getName() возвращает верное значение")
    @ParameterizedTest
    @CsvSource({
            "black bun",
            "white bun",
            "red bun"
    })
    void getNameReturnsConstructorValue(String name) {
        Bun bun = new Bun(name, 100.0f);
        assertEquals(name, bun.getName());
    }

    @DisplayName("Получение цены булочки")
    @Description("Проверяем, что метод getPrice() возвращает верное значение")
    @ParameterizedTest
    @CsvSource({
            "100.0",
            "200.0",
            "300.0"
    })
    void getPriceReturnsConstructorValue(float price) {
        Bun bun = new Bun("bun", price);
        assertEquals(price, bun.getPrice());
    }
}
