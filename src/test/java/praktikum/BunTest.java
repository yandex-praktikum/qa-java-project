package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunTest {

    private Bun bunObject;
    private String name;
    private float price;

    public BunTest(String name, float price){
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] data(){
        return new Object[][]{
                {"Самец", 0f},
                {"Самка", 12f},
                {"Бабабой", 1.001f}
        };
    }

    @Before
    public void setUp(){
        bunObject = new Bun(name, price);
    }

    @Test
    public void getNameShouldReturnNameTest() {
        Assert.assertEquals(name, bunObject.getName());
    }

    @Test
    public void getPriceShouldReturnPriceTest() {
        Assert.assertEquals(price, bunObject.getPrice(), 0.001f);

    }


}