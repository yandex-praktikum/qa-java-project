package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    private static String name = "Пшеничная";
    private static float price = 100.0F;
    Bun bun = new Bun(name, price);

    @Test
    @DisplayName("Получение названия булочки")
    public void getNameOfBunTest() {
        String actualName = bun.getName();
        assertEquals(name,
                    actualName,
                    String.format("Неверное название булочки: ожидалось \"%s\", фактически - \"%s\"", name, actualName)
        );
    }

    @Test
    @DisplayName("Получение стоимости булочки")
    public void getPriceOfBunTest() {
        float actualPrice = bun.getPrice();
        assertEquals(price,
                    actualPrice,
                    String.format("Неверная стоимость булочки: ожидалось \"%f\", фактически - \"%f\"", price, actualPrice)
        );
    }



}