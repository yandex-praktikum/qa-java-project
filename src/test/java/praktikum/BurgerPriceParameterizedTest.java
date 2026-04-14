package praktikum;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest extends BaseTest {

    private List<Ingredient> ingredients;
    private float expectedPrice;

    @Parameters(name = "Булочка {0} руб., ингредиенты {1} → итого {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // Булочка 100 руб., 0 ингредиентов → 200 руб.
                {100.0f, new ArrayList<>(), 200.0f},
                // Булочка 150 руб., 1 ингредиент 50 руб. → 350 руб.
                {150.0f, Arrays.asList(createIngredientMock(50.0f)), 350.0f},
                // 2 Булочки 200 руб., 3 ингредиента (100, 150, 75) → 625 руб.
                {200.0f, Arrays.asList(
                        createIngredientMock(100.0f),
                        createIngredientMock(150.0f),
                        createIngredientMock(75.0f)
                ), 725.0f},
                // Булочка 0 руб., 2 ингредиента по 10 руб. → 20 руб.
                {0.0f, Arrays.asList(
                        createIngredientMock(10.0f),
                        createIngredientMock(10.0f)
                ), 20.0f}
        });
    }

    private static Ingredient createIngredientMock(float price) {
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    public BurgerPriceParameterizedTest(float bunPrice, List<Ingredient> ingredients, float expectedPrice) {
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
        this.bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(bunMock.getName()).thenReturn("test bun");
        this.burger = new Burger();
    }

    @Override
    @Before
    public void setUp() {
        burger.setBuns(bunMock);
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                burger.addIngredient(ingredient);
            }
        }
    }

    @Test
    public void testGetPrice() {
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001f);
    }
}
