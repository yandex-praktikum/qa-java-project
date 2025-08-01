package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunParamTest {

    private String name;
    private float price;
    private Bun bun;

    public BunParamTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "name: {0}, price: {1}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Супербулка", 30.0f},
                {"Первый сорт", 20.0f},
                {"Второй сорт", 10.0f},
                {"", 0.0f},
                {null, 0.0f},
                {"СуперСупер...", 0.0f},
                {"$%&!", 0.0f},
                {"Супербулка", -30.0f},
                {"Супербулка", 0},
                {"Супербулка", 0.0000000000000000001f},
                {"Супербулка", 9999999999.999f},
        };
    }

    @Before
    public void initBun() {
        bun = new Bun(name, price);
    }

    @Test
    public void getNameBunTest() {
        assertEquals("Имя булки не совпадает", name, bun.getName());
    }

    @Test
    public void getPriceBunTest() {
        assertEquals("Цена булки не совпадает", price, bun.getPrice(), 0.5f);
    }
}
