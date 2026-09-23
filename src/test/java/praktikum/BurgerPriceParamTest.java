package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Параметризованный тест getPrice (тест расчёта цены)
 */
@RunWith(Parameterized.class)
public class BurgerPriceParamTest {
    @Mock
    private Bun bun;
    @Mock
    private Ingredient sauce;
    @Mock
    private Ingredient filling;

    private Burger burger;

    private final float bunPrice;
    private final float saucePrice;
    private final float fillingPrice;
    private final float expectedTotalPrice;

    public BurgerPriceParamTest(float bunPrice, float saucePrice, float fillingPrice, float expectedTotalPrice) {
        this.bunPrice = bunPrice;
        this.saucePrice = saucePrice;
        this.fillingPrice = fillingPrice;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {100f, 50f, 70f, 320f},
            {0f, 0f, 0f, 0f},
            {200f, 100f, 0f, 500f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void getPriceShouldCalculateCorrectTotal() {
        when(bun.getPrice()).thenReturn(bunPrice);
        when(sauce.getPrice()).thenReturn(saucePrice);
        when(filling.getPrice()).thenReturn(fillingPrice);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(expectedTotalPrice, burger.getPrice(), 0.001f);
    }
}
