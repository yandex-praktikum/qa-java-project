import praktikum.Bun;
import praktikum.Burger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final float bunPrice;
    private final float expectedPrice;

    public BurgerParameterizedTest(float bunPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Цена булочки: {0}, цена бургера: {1}")
    public static Object[][] bunPrices() {
        return new Object[][]{
                {100F, 200F},
                {200F, 400F},
                {300F, 600F},
                {0F, 0F},
        };
    }

    @Test
    public void getPriceDependsOnBunPrice() {
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Burger burger = new Burger();
        burger.setBuns(bun);

        assertEquals(expectedPrice, burger.getPrice(), 0);
    }
}
