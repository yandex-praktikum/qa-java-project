
import org.junit.Test;
import praktikum.Bun;



import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test
    public void getBunNameTest() {
        Bun bun = new Bun("Black Bun", 100.0f);
        String checkedBunName = bun.getName();
        assertEquals("Black Bun", checkedBunName);
    }

    @Test
    public void getBunPriceTest() {
        Bun bun = new Bun("White Bun", 150.0f);
        float checkedBunPrice = bun.getPrice();
        assertEquals(150.0f, checkedBunPrice, 0.01);
    }
}
