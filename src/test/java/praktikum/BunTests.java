package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTests {

    @Test
    void testGetNameReturnsBunName() {

        String expectedBunName = "black bun";
        float price = 100;
        Bun bun = new Bun(expectedBunName, price);
        String actualBunName = bun.getName();

        assertEquals(expectedBunName, actualBunName);
    }

    @Test
    void testGetPriceReturnsBunPrice() {

        String bunName = "black bun";
        float expectedBunPrice = 100;
        Bun bun = new Bun(bunName, expectedBunPrice);
        float actualBunPrice = bun.getPrice();

        assertEquals(expectedBunPrice, actualBunPrice);
    }

}
