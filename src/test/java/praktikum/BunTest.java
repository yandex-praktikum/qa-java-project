package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BunTest {

    private static final String BUN_NAME = "black bun";
    private static final float BUN_PRICE = 100f;
    private static final float DELTA = 0.001f;

    @Test
    public void getNameShouldReturnCorrectName() {
        Bun bun = new Bun(BUN_NAME, BUN_PRICE);
        assertEquals(BUN_NAME, bun.getName());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Bun bun = new Bun(BUN_NAME, BUN_PRICE);
        assertEquals(BUN_PRICE, bun.getPrice(), DELTA);
    }
}