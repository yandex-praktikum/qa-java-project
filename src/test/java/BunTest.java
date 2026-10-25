package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    @Parameters(name = "Bun({0}, {1}) хранит название и цену")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"black bun", 100f},
                {"white bun", 200f},
                {"red bun",   300f},
                {"",          0f},
                {"bun",       0.5f}
        });
    }

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Test
    public void constructorStoresName() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void constructorStoresPrice() {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.001f);
    }

    @Test
    public void getNameReturnsFieldValue() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceReturnsFieldValue() {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.001f);
    }
}