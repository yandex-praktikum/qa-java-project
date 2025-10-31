package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunParameterizedTest {

    @Parameterized.Parameters(name = "name={0}, price={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100f},
                {"white bun", 0f},
                {"sesame bun", 199.99f},
        });
    }

    @Parameterized.Parameter(0)
    public String name;

    @Parameterized.Parameter(1)
    public float price;

    @Test
    public void getName_returnsConstructorName() {
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void getPrice_returnsConstructorPrice() {
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(), 0.0001f);
    }
}


