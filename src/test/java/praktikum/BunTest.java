package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class BunTest {

    @Test
    public void constructorShouldSetNameAndPrice() {
        String expectedName = "Sesame Bun";
        float expectedPrice = 25.5f;
        
        Bun bun = new Bun(expectedName, expectedPrice);
        
        assertEquals(expectedName, bun.getName());
        assertEquals(expectedPrice, bun.getPrice(), 0.001f);
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        String expectedName = "Brioche Bun";
        Bun bun = new Bun(expectedName, 30.0f);
        
        assertEquals(expectedName, bun.getName());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        float expectedPrice = 22.75f;
        Bun bun = new Bun("Whole Wheat Bun", expectedPrice);
        
        assertEquals(expectedPrice, bun.getPrice(), 0.001f);
    }

    @Test
    public void constructorShouldHandleEmptyName() {
        String emptyName = "";
        float price = 15.0f;
        
        Bun bun = new Bun(emptyName, price);
        
        assertEquals(emptyName, bun.getName());
        assertEquals(price, bun.getPrice(), 0.001f);
    }

    @Test
    public void constructorShouldHandleZeroPrice() {
        String name = "Free Bun";
        float zeroPrice = 0.0f;
        
        Bun bun = new Bun(name, zeroPrice);
        
        assertEquals(name, bun.getName());
        assertEquals(zeroPrice, bun.getPrice(), 0.001f);
    }

}