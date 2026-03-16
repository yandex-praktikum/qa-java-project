import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

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

    @Parameterized.Parameters(name = "name={0}, price={1}")
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100f},
                {"white bun", 255.5f},
                {"булочка", 0f}
        });
    }

    @Test
    public void bunStoresNameCorrectly() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void bunStoresPriceCorrectly() {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.0001f);
    }
}
