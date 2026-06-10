package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тест булочки -> Имя: {0}, Цена: {1}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {"Краторная булка", 300.0f},
                {"White Bun", 150.55f},
                {"", 0.0f},
                {"Булка со спецсимволами №1!", 9999.99f}
        });
    }

    @Test
    public void testBunGetNameReturnsCorrectValue() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals("Имя булочки не совпадает с ожидаемым", name, bun.getName());
    }

    @Test
    public void testBunGetPriceReturnsCorrectValue() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals("Цена булочки не совпадает с ожидаемой", price, bun.getPrice(), 0.0f);
    }
}
