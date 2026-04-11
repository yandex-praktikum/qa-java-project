package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private static final float BUN_PRICE = 100f;
    private static final float SAUCE_PRICE = 50f;
    private static final float FILLING_PRICE = 30f;

    private Burger burger;
    private Bun bunMock;
    private final List<Ingredient> ingredients;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(List<Ingredient> ingredients, float expectedPrice) {
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Ингредиенты: {0}, ожидаемая цена: {1}")
    public static Collection<Object[]> data() {
        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE);

        Ingredient sauceIngredient = mock(Ingredient.class);
        when(sauceIngredient.getPrice()).thenReturn(SAUCE_PRICE);
        Ingredient fillingIngredient = mock(Ingredient.class);
        when(fillingIngredient.getPrice()).thenReturn(FILLING_PRICE);

        return Arrays.asList(
                new Object[][]{
                        {Arrays.asList(sauceIngredient), BUN_PRICE * 2 + SAUCE_PRICE},
                        {Arrays.asList(sauceIngredient, fillingIngredient), BUN_PRICE * 2 + SAUCE_PRICE + FILLING_PRICE},
                        {Arrays.asList(), BUN_PRICE * 2}
                }
        );
    }

    @Before
    public void setUp() {
        burger = new Burger();
        bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(BUN_PRICE);
        burger.setBuns(bunMock);
        for (Ingredient ing : ingredients) {
            burger.addIngredient(ing);
        }
    }

    @Test
    public void testGetPrice() {
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}