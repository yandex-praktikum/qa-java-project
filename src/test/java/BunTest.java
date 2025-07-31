


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import praktikum.Bun;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class BunTest {

    @Test
    @Parameters({
            "White Bun, 5.5",
            "Black Bun, 7.0",
            "Sesame Bun, 8.5",
            "Gluten-Free Bun, 9.5"
    })
    public void testBunProperties(String name, float price) {

        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice(), 0.01);
    }
}
