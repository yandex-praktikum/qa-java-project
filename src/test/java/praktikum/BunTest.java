package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    @ParameterizedTest
    @CsvSource({
            "black bun, 100.0",
            "white bun, 0.0",
            "red-bun 123!, 250.55",
            "'', -10.0"
    })
    void getNameReturnsExpectedName(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @ParameterizedTest
    @CsvSource({
            "black bun, 100.0",
            "white bun, 0.0",
            "red bun, 250.55",
            "free bun, -10.0"
    })
    void getPriceReturnsExpectedPrice(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.001f);
    }
}