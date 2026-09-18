package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {
    private final String expectedName = "С кунжутом";
    private final float expectedPrice = 50;
    private final Bun bun = new Bun(expectedName, expectedPrice);

    @Test
    @DisplayName("Проверка геттера названия булочки")
    public void getNameSuccess() {
        String actualName = bun.getName();
        assertEquals(expectedName, actualName, "getName() возвращает некорректное название");
    }

    @Test
    @DisplayName("Проверка геттера цены булочки")
    public void getPriceSuccess() {
        float actualPrice = bun.getPrice();
        assertEquals(expectedPrice, actualPrice, "getPrice() возвращает некорректную цену");
    }
}
