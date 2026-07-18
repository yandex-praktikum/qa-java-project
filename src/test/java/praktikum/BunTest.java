package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test
    public void getNameShouldReturnBunName() {
        Bun bun = new Bun("White bun", 50.50f);
        String actual = bun.getName();
        assertEquals(
                "White bun",
                actual,
                "Имя булочки не соответствует ожидаемому");
    }

    @Test
    public void getPriceShouldReturnBunPrice() {
        Bun bun = new Bun("Black bun", 60.50f);
        float actual = bun.getPrice();
        assertEquals(
                60.50f,
                actual,
                "Цена булочки не соответствует ожидаемой");
    }
}
