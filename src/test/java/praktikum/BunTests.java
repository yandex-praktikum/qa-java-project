package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Класс Bun - модель булочки для бургера")
public class BunTests {


    @DisplayName("Конструктор должен корректно устанавливать значения полей")
    @ParameterizedTest
    @CsvSource({
            "black bun, 100",  // нормальные значения
            "white bun, 200", // другие нормальные значения
            "test Bun, 0",                    // нулевая цена
            "Булка, 99.01"                    // дробная цена
    })
    void testBunConstructorGetters(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName(), "Название булочки должно совпадать");
        assertEquals(price, bun.getPrice(), "Цена булочки должна совпадать");
    }


}
