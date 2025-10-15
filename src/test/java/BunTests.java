import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTests {

    @ParameterizedTest
    @DisplayName("проверить у булок разные комбинации имён и цен")
    @CsvSource({
            "black bun, 100",
            "white bun, 200",
            "red bun, 300"
    })
    public void bunStoresNameAndPriceCorrectly(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName(), "Название булочки должно совпадать");
        assertEquals(price, bun.getPrice(), "Цена булочки должна совпадать");
    }
}
