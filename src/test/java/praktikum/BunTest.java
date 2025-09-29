package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class BunTest {

    @Test
    public void testGetNameReturnsCorrectName() {
        Bun bun = new Bun("Белый", 2.5f);
        assertEquals("Белый", bun.getName());
    }

    @Test
    public void testGetPriceReturnsCorrectPrice() {
        Bun bun = new Bun("Ржаной", 3.1f);
        assertEquals(3.1f, bun.getPrice(), 0.01f);
    }
}