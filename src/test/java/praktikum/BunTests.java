package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTests {

    @Test
    public void shouldReturnName() {
        Bun bun = new Bun("black bun", 100f);
        assertEquals("black bun", bun.getName());
    }

    @Test
    public void shouldReturnPrice() {
        Bun bun = new Bun("white bun", 200f);
        assertEquals(200f, bun.getPrice(), 0.0001f);
    }
}
