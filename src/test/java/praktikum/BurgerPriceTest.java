package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private Burger burger;
    private final String description;
    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final float expectedPrice;

    public BurgerPriceTest(String description, Bun bun, List<Ingredient> ingredients, float expectedPrice) {
        this.description = description;
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] getTestData() {

        Bun cheapBun = mock(Bun.class);
        when(cheapBun.getPrice()).thenReturn(100f);

        Bun expensiveBun = mock(Bun.class);
        when(expensiveBun.getPrice()).thenReturn(200f);

        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getPrice()).thenReturn(50f);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getPrice()).thenReturn(80f);

        return new Object[][]{
                {"Только булочка", cheapBun, List.of(), 200f},
                {"Булочка + соус", cheapBun, List.of(sauce), 250f},
                {"Дорогая булочка + начинка", expensiveBun, List.of(filling), 480f},
                {"Булочка + соус + начинка", cheapBun, Arrays.asList(sauce, filling), 330f}
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void getPriceShouldReturnCorrectSum() {
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}