import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTest {

    private String expectedName = "Double Big burger";
    private float expectedPrice = 411.65f;
    private Bun bun;

    @Before
    public void setUp() {
        bun = new Bun("Double Big burger", 411.65f);
    }

    @Test
    public void getNameTest(){
        assertEquals("Ожидаемое имя не совпадает с актуальным", expectedName, bun.getName());
    }

    @Test
    public void getPriceTest(){
        assertEquals("Ожидаемая цена не совпадает с актуальной ценой", expectedPrice, bun.getPrice(), 0);
    }

}
