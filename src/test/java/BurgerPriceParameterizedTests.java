import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTests {
    private Burger burger;
    private Bun bun;
    private Ingredient sauce;
    private Ingredient filling;

    private final float bunPrice;
    private final float saucePrice;
    private final float fillingPrice;
    private static final float DELTA = 0.00001f;

    public BurgerPriceParameterizedTests(float bunPrice, float saucePrice, float fillingPrice) {
        this.bunPrice = bunPrice;
        this.saucePrice = saucePrice;
        this.fillingPrice = fillingPrice;
    }

    @Parameterized.Parameters(name = "Стоимость булочки: {0}, соуса: {1}, начинка: {2}")
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                {100f, 200f, 300f},
                {200f, 300f, 100f},
                {300f, 100f, 200f},
                {100f, 100f, 100f},
                {200f, 100f, 100f},
                {300f, 100f, 100f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();
        bun = mock(Bun.class);
        sauce = mock(Ingredient.class);
        filling = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(bunPrice);
        when(sauce.getPrice()).thenReturn(saucePrice);
        when(filling.getPrice()).thenReturn(fillingPrice);
    }

    @Test
    public void burgerPriceParameterizedTest() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float expectedTotalPrice = bunPrice * 2 + saucePrice + fillingPrice;
        assertEquals(expectedTotalPrice, burger.getPrice(), DELTA);
    }
}