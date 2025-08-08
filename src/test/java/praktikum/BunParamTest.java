package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunParamTest {

    private static final float DELTA = 0.001f; // допустимая погрешность сравнения цен

    private final String name;
    private final float price;

    public BunParamTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"Флюоресцентная булка R2-D3", 988f},
                {"Краторная булка N-200i", 1255f}
        });
    }

    @Test
    public void bunShouldReturnCorrectName() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void bunShouldReturnCorrectPrice() {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), DELTA);
    }
}
