package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] getData() {
        return new Object[][] {
                {"Черная булочка", 100f},
                {"Белая булочка", 200f}
        };
    }

    @Test
    public void getNameReturnsCorrectName() {
        Bun bun = new Bun(name, price);
        assertEquals("Имя булочки должно совпадать", name, bun.getName());
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        Bun bun = new Bun(name, price);
        assertEquals("Цена булочки должна совпадать", price, bun.getPrice(), 0.0f);
    }
}