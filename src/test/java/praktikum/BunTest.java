package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    @Parameterized.Parameters(name = "name={0}, price={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"White", 100f},
                {"Black", 199.99f},
                {"Sesame", 0f}
        });
    }

    @Parameterized.Parameter(0)
    public String name;

    @Parameterized.Parameter(1)
    public float price;

    @Test
    public void bunGettersReturnConstructorParameters() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice(), 0.0001f);
    }
}
