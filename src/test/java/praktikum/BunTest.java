package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test
    public void getNameShouldReturnCorrectName() {
        Bun bun = new Bun("black bun", 100f);
        assertEquals("black bun", bun.getName());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Bun bun = new Bun("black bun", 100f);
        assertEquals(100f, bun.getPrice(), 0.001);
    }
}