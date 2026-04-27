package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "name = {0}, price = {1}")
    public static Collection<Object[]> getBunData() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100.0f},
                {"white bun", 200.5f},
                {"red bun", 300.0f}
        });
    }

    @Test
    public void getNameReturnsBunName() {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceReturnsBunPrice() {
        Bun bun = new Bun(name, price);

        assertEquals(price, bun.getPrice(), 0.001f);
    }
}