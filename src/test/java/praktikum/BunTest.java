package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BunTest {
    @ParameterizedTest
    @ValueSource(floats = {100, 200, 3.1415f})
    @DisplayName("Проверка getPrice")
    public void testGetPrice(float price) {
        Bun bun = new Bun(null, price);
        assertEquals(price, bun.getPrice());
    }

    @ParameterizedTest
    @ValueSource(strings = {"белая", "красная", "черная" })
    @DisplayName("Проверка getName")
    public void testGetName(String name) {
        Bun bun = new Bun(name, 100);
        assertEquals(name, bun.getName());
    }

}