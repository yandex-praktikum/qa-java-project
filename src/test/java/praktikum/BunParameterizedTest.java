package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunParameterizedTest {

    private final String name;
    private final float price;

    public BunParameterizedTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "bun name={0}, price={1}")
    public static Object[][] data() {
        return new Object[][]{
                {"black bun", 100.0f},
                {"white bun", 0.0f},
                {"very long bun name value", 999.99f},
                {"", 50.5f}
        };
    }

    @Test
    public void getNameReturnsSameNameAsInConstructor() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceReturnsSamePriceAsInConstructor() {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.0001f);
    }
}