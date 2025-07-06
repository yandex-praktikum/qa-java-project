package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunParameterizedTest {
    
    private String name;
    private float price;

    public BunParameterizedTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Classic Bun", 20.0f},
                {"Sesame Bun", 25.5f},
                {"Whole Wheat Bun", 22.75f},
                {"Brioche Bun", 30.0f},
                {"", 0.0f}
        });
    }

    @Test
    public void bunShouldBeCreatedWithCorrectParameters() {
        Bun bun = new Bun(name, price);
        
        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice(), 0.001f);
    }
}