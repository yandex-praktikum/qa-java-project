package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

public class BunTest {

    @Test
    public void getName_ReturnsCorrectName() {
        Bun bun = new Bun("Black bun", 100);
        assertEquals("Black bun", bun.getName());
    }

    @Test
    public void getPrice_ReturnsCorrectPrice() {
        Bun bun = new Bun("White bun", 150.5f);
        assertEquals(150.5f, bun.getPrice(), 0.001);
    }

    @RunWith(Parameterized.class)
    public static class BunParameterizedTest {
        private final String name;
        private final float price;

        public BunParameterizedTest(String name, float price) {
            this.name = name;
            this.price = price;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"Black bun", 100f},
                    {"White bun", 200f},
                    {"Red bun", 300f},
                    {"Gluten-free bun", 0f},
                    {"", -50f}
            });
        }

        @Test
        public void constructor_SetsFieldsCorrectly() {
            Bun bun = new Bun(name, price);
            assertEquals(name, bun.getName());
            assertEquals(price, bun.getPrice(), 0.001);
        }
    }
}