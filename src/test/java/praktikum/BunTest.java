package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class BunTest {
    private final String expectedName;
    private final float expectedPrice;
    private Bun bun;

    public BunTest(String expectedName, float expectedPrice) {
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] getBunData() {
        return new Object[][]{
                {"Краторная булка N-200i", Float.MAX_VALUE},
                {"crater bun N-200i", Float.MIN_VALUE},
                {"", 100.5f},
                {"!@#$%^&*()_+{}:", 456f},
                {"Флюоресцентная булка R2-D3", 0f}
        };
    }

    @Test
    public void testNameNotNull() {
        bun = new Bun(expectedName, expectedPrice);
        Assert.assertNotNull(bun.getName());
    }

    @Test
    public void testNameEqualsExpected() {
        bun = new Bun(expectedName, expectedPrice);
        Assert.assertEquals(expectedName, bun.getName());
    }

    @Test
    public void testNameEqualsUsingEqualsMethod() {
        bun = new Bun(expectedName, expectedPrice);
        Assert.assertTrue(bun.getName().equals(expectedName));
    }

    @Test
    public void testPriceIsNonNegative() {
        bun = new Bun(expectedName, expectedPrice);
        Assert.assertTrue(bun.getPrice() >= 0);
    }

    @Test
    public void testPriceEqualsExpected() {
        bun = new Bun(expectedName, expectedPrice);
        Assert.assertEquals(expectedPrice, bun.getPrice(), 0);
    }

    @Test
    public void testPriceEqualsUsingReverseOrder() {
        bun = new Bun(expectedName, expectedPrice);
        Assert.assertEquals(bun.getPrice(), expectedPrice, 0);
    }

    @Test
    public void testEmptyName() {
        new Bun("", Float.MAX_VALUE);
    }

    @Test
    public void testNullName() {
        new Bun(null, Float.MAX_VALUE);
    }

    @Test
    public void testNegativePrice() {
        new Bun("test", -100);
    }
}
