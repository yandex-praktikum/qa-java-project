
package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class BunTest {

    @Test
    public void shouldReturnCorrectName() {
        Bun bun = new Bun("Black Bun", 100);
        assertEquals("Black Bun", bun.getName());
    }

    @Test
    public void shouldReturnCorrectPrice() {
        Bun bun = new Bun("Red Bun", 200);
        assertEquals(200, bun.getPrice(), 0);
    }
}
