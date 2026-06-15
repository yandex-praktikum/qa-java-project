package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test
public void getNameReturnCorrectName(){
        Bun bun = new Bun("Флюоресцентная булка R2-D3", 988f);

        assertEquals("Флюоресцентная булка R2-D3", bun.getName());
    }

    @Test
    public void getPriceReturnCorrectPrice(){
        Bun bun = new Bun("Флюоресцентная булка R2-D3", 988f);
        assertEquals(988f, bun.getPrice(), 0.001f); // погрешность - 0.001 float
    }
}
