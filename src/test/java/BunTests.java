package praktikum;
import org.junit.Assert;
import org.junit.Test;


public class BunTests {

    @Test
    public void testGetBunName() {
        String name = "TestName";
        Bun bun = new Bun(name, 0.0f);
        Assert.assertEquals("Имена не совпадают", name, bun.getName());
    }

    @Test
    public void testGetBunPrice() {
        Float price = 0.1f;
        Bun bun = new Bun("", price);
        Assert.assertEquals("Цены не совпадают", price, bun.getPrice(), 0.0);
    }

    @Test
    public void testBunConstruktor() {
        String name = "TestName";
        Float price = 0.2f;
        Bun bun = new Bun(name, price);
        Assert.assertEquals("Имена не совпадают", name, bun.getName());
        Assert.assertEquals("Цены не совпадают", price, bun.getPrice(), 0.0f);
    }

}