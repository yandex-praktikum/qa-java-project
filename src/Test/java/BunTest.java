import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.*;

public class BunTest {

    private static final String BUN_NAME = "black bun";
    private static final float BUN_PRICE = 100;

    @Test
    public void constructorShouldSetFieldsCorrectly() {
        Bun bun = new Bun(BUN_NAME, BUN_PRICE);

        assertEquals("Name should be set correctly", BUN_NAME, bun.name);
        assertEquals("Price should be set correctly", BUN_PRICE, bun.price, 0.01);
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        Bun bun = new Bun(BUN_NAME, BUN_PRICE);

        assertEquals("getName() should return correct name", BUN_NAME, bun.getName());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Bun bun = new Bun(BUN_NAME, BUN_PRICE);

        assertEquals("getPrice() should return correct price", BUN_PRICE, bun.getPrice(), 0.01);
    }
}
