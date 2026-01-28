package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class BunTest {

    @Test
    public void constructorShouldSetNameAndPrice() {
        Bun bun = new Bun("black bun", 1.25f);

        assertEquals("black bun", bun.getName());
        assertEquals(1.25f, bun.getPrice(), 0.0001f);
    }

    @Test
    public void getNameShouldReturnProvidedName() {
        Bun bun = new Bun("white bun", 2.0f);

        assertEquals("white bun", bun.getName());
    }

    @Test
    public void getPriceShouldReturnProvidedPrice() {
        Bun bun = new Bun("rye bun", 3.5f);

        assertEquals(3.5f, bun.getPrice(), 0.0001f);
    }
}
