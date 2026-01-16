import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTest {
    @Test
    public void getNameTest() {
        Bun bun = new Bun("Флюоресцентная булка", 988);
        assertEquals("Флюоресцентная булка", bun.getName());
    }

    @Test
    public void getPriceTest() {
        Bun bun = new Bun("Краторная булка", 1255);
        assertEquals(1255, bun.getPrice(), 0);
    }
}
