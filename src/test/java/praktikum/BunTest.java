package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {
    @Test
    public void getNameShouldReturnCorrectName() {
        Bun bun = new Bun("Флюоресцентная булка R2-D3", 125);
        assertEquals("Флюоресцентная булка R2-D3", bun.getName());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Bun bun = new Bun("Флюоресцентная булка R2-D3", 125);
        assertEquals(125f, bun.getPrice(), 0.001);
    }
}