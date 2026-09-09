package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {
    private Bun bun;

    @BeforeEach
    public void setUp(){
        bun = new Bun("cheese bun",85.0f);
    }

    @Test
    void testGetNamePositiveResult() {
        assertEquals("cheese bun", bun.getName());
    }

    @Test
    void testGetPricePositiveResult() {
        assertEquals(85.0f, bun.getPrice(), 0.01f);
    }
}

