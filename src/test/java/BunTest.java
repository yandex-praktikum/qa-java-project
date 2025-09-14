import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    public static Object[][] testData() {
        Object[][] testData = {
                {"Круглая булка", 100},
                {"Квадратная булка", 150},
                {"", 1000},
                {"Без булки", 0}
        };
        return testData;
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void getNameTest(String name, float price) {
        Bun bun = new Bun(name, price);
        String actualName = bun.getName();
        assertEquals(name, actualName, "Имя не соотвествует заданному!");
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void getPriceTest(String name, float price) {
        Bun bun = new Bun(name, price);
        float actualPrice = bun.getPrice();
        assertEquals(price, actualPrice, "Цена не соотвествует заданной!");
    }

}
