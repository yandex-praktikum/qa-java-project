package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    @ParameterizedTest(name = "name = {0}, price = {1}")
    @CsvSource({
            "black bun, 100",
            "white bun, 200",
            "red bun, 300",
            "Флюгерхаус, 9.99"
    })
    @DisplayName("Геттер getName возвращает имя булочки")
    void getNameReturnsProvidedName(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
    }

    @ParameterizedTest(name = "name = {0}, price = {1}")
    @CsvSource({
            "black bun, 100",
            "white bun, 200",
            "red bun, 300",
            "Флюгерхаус, 9.99"
    })
    @DisplayName("Геттер getPrice возвращает цену булочки")
    void getPriceReturnsProvidedPrice(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(price, bun.getPrice(), 0.001f);
    }
}
