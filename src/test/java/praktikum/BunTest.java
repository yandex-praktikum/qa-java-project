package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test
    public void getNameReturnCorrectName() {
        Bun bun = new Bun("white bun", 200.0f);
        String actualName = bun.getName();
        assertEquals("white bun", actualName);
    }

    @Test
    public void getPriceReturnCorrectPrice() {
        Bun bun = new Bun("white bun", 300.0f);
        float actualPrice = bun.getPrice();
        assertEquals(300.0f, actualPrice, 0.0f);
    }
}