package tests;

import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test
    public void getNameShouldReturnCorrectName() {
        Bun bun = new Bun("black bun", 100f);

        String name = bun.getName();

        assertEquals("black bun", name);
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Bun bun = new Bun("black bun", 100f);

        float price = bun.getPrice();

        assertEquals(100f, price, 0.001f);
    }
}