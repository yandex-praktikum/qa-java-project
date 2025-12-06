package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test
    public void getNameReturnsCorrectName() {
        Bun bun = new Bun("white bun", 100f);
        assertEquals("white bun", bun.getName());
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        Bun bun = new Bun("black bun", 150f);
        assertEquals(150f, bun.getPrice(), 0.001);
    }
}