package praktikum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {
    private Bun bun;

    public static Object[][] data() {
        return new Object[][]{
                {"Белая булка", 50},
                {"Черная булка", 60},
                {"Безглютеновая булка", 999},
                {"", 80},
                {"fg&!!", 10}
        };
    }

    @AfterEach
    public void tearDown() {
        bun.name = null;
        bun.price = 0;
        bun = null;
    }

    @ParameterizedTest
    @MethodSource("data")
    public void getNameTest(String name, float price) {
        bun = new Bun(name, price);
        assertEquals(name, bun.getName(), "Ожидается, что вернется название булки: " + name);
    }

    @ParameterizedTest
    @MethodSource("data")
    public void getPriceTest(String name, float price) {
        bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), "Ожидается, что вернется цена: " + price);
    }
}
