package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class BunTest {

    @Test
    public void bunHasCorrectName() {
        Bun bun = new Bun("black bun", 100);
        Assert.assertEquals("black bun", bun.getName());
    }

    @Test
    public void bunHasCorrectPrice() {
        Bun bun = new Bun("white bun", 200);
        Assert.assertEquals(200, bun.getPrice(), 0.01);
    }
}
