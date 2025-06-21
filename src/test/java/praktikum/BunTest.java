package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {

    private Bun bun;

    @Before
    public void setUp() {
       bun = new Bun("name", (float) 1.0);
    }

    @Test
    public void getName() {
        assertEquals("name", bun.getName());
    }

    @Test
    public void getPrice() {
        assertEquals((float) 1.0, bun.getPrice(), 0.0);
    }

}