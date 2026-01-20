package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    @ParameterizedTest
    @CsvSource({
            "'White', 10.f",
            "'Black', 15.f"
    })
    void getName(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @ParameterizedTest
    @CsvSource({
            "'White', 10.f",
            "'Black', 15.f"
    })
    void getPrice(String name, float price) {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice());
    }
}