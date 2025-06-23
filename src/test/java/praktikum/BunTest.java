package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;
    private Bun bun;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Булочка для бургера", 100.0f},
                {"Черная булочка", 150.0f},
                {"Безглютеновая булочка", 200.0f}
        };
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    public void testCreateBunWithCorrectName() {
        assertEquals(name, bun.getName());
    }

    @Test
    public void testCreateBunWithCorrectPrice() {
        assertEquals(price, bun.getPrice(), 0.0f);
    }

    @Test
    public void testReturnCorrectNameFromGetter() {
        assertEquals(name, bun.getName());
    }
}
