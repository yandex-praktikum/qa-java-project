import org.junit.Test;
import org.junit.Before;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTest {
    private Bun bun;

    @Before
    public void createNewInstance() {
        bun = new Bun("original", 200.0f);
    }

    @Test
    public void getName() {
        String expected = "original";
        String actual = bun.getName();

        assertEquals("Неверное значение названия булочки", expected, actual);
    }

    @Test
    public void getPrice() {
        float expected = 200.0f;
        float actual = bun.getPrice();

        assertEquals("Неверные значения цены булочки", expected, actual, 0);
    }
}