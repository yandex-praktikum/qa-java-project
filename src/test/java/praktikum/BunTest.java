package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {

    private final String bunName;
    private final float bunPrice;

    public BunTest(String bunName, float bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"black bun", 100.0f},
                {"white bun", 200.0f},
                {"red bun", 300.0f},
                {"special bun", 150.0f}
        });
    }

    @Test
    public void bunConstructorSetsNameCorrectly() {
        Bun bun = new Bun(bunName, bunPrice);

        assertEquals(bunName, bun.getName());
    }

    @Test
    public void bunConstructorSetsPriceCorrectly() {
        Bun bun = new Bun(bunName, bunPrice);

        assertEquals(bunPrice, bun.getPrice(), 0.001);
    }

    @Test
    public void bunGetNameReturnsCorrectName() {
        Bun bun = new Bun(bunName, bunPrice);

        String name = bun.getName();

        assertEquals(bunName, name);
    }

    @Test
    public void bunGetPriceReturnsCorrectPrice() {
        Bun bun = new Bun(bunName, bunPrice);

        float price = bun.getPrice();

        assertEquals(bunPrice, price, 0.001);
    }
}