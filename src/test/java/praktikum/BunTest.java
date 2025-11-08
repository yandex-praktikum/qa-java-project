package praktikum;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class BunTest {

    private Bun bun;

    @Before
    public void setUp() {
        bun = new Bun("Кунжут+Орегано", 0.03F);
    }

    @Test
    public void testTestGetName() {
        assertEquals("Кунжут+Орегано", bun.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(0.03F, bun.getPrice(), 0.01);
    }
}