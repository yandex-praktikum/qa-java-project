package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test
    public void shouldReturnCorrectName() {
        Bun bun = new Bun("black bun", 100);

        assertEquals("black bun", bun.getName());
    }

    @Test
    public void shouldReturnCorrectPrice() {
        Bun bun = new Bun("black bun", 100);

        assertEquals(100, bun.getPrice(), 0);
    }
}
