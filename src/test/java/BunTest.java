import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {

    private final String bunName;
    private final float bunPrice;

    public BunTest(String bunName, float bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    @Parameterized.Parameters(name = "Булочка: {0}, цена: {1}")
    public static Object[][] getBunData() {
        return new Object[][] {
                {"black bun", 100},
                {"white bun", 200},
                {"red bun", 150.5f}
        };
    }


    @Test
    public void getName_WhenBunCreated_ReturnsCorrectName() {
        Bun bun = new Bun(bunName, bunPrice);
        assertEquals(bunName, bun.getName());
    }

    @Test
    public void getPrice_WhenBunCreated_ReturnsCorrectPrice() {
        Bun bun = new Bun(bunName, bunPrice);
        assertEquals(bunPrice, bun.getPrice(), 0.001);
    }
}