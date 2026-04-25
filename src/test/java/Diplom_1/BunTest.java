package Diplom_1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private String bunName;
    private float bunPrice;

    public BunTest(String bunName, float bunPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {"Флюоресцентная булка R2-D3", 100},
                {"", Float.MIN_VALUE},
                {null, -Float.MAX_VALUE},
                {String.format("%25c", ' ').replaceAll(" ", "\\" + "о")
                        + "чень дл" + String.format("%25c", ' ').replaceAll(" ", "\\" + "и")
                        + "нная стр" + String.format("%25c", ' ').replaceAll(" ", "\\" + "о")
                        + "ка", Float.MAX_VALUE},
                {"!@#$%^&*()", 0},
                {"Краторная булка N-200i", -0.01f}
        };
    }

    @Test
    public void getBunName() {
        Bun bun = new Bun(bunName, bunPrice);
        String actualBunName = bun.getName();
        assertEquals(bunName, actualBunName);
    }

    @Test
    public void getBunPrice() {
        Bun bun = new Bun(bunName, bunPrice);
        float actualBunPrice = bun.getPrice();
        Assert.assertEquals(bunPrice, actualBunPrice, 0.00001);
    }
}