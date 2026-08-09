package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerGetPriceTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientFirst;

    @Mock
    private Ingredient mockIngredientSecond;

    @Mock
    private Ingredient mockIngredientThird;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void getPriceShouldReturnCorrectPriceWhenBunAndIngredientsExist() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredientFirst.getPrice()).thenReturn(50f);
        when(mockIngredientSecond.getPrice()).thenReturn(75f);
        when(mockIngredientThird.getPrice()).thenReturn(25f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        float result = burger.getPrice();

        assertEquals("Цена бургера должна быть 350 (100*2 + 50 + 75 + 25)", 350f, result, 0.001f);
    }

    @Test
    public void getPriceShouldReturnOnlyBunPriceWhenNoIngredients() {
        when(mockBun.getPrice()).thenReturn(150f);
        burger.setBuns(mockBun);

        float result = burger.getPrice();

        assertEquals("Цена должна быть 300 (цена булочки * 2)", 300f, result, 0.001f);
    }

    @Test
    public void getPriceShouldReturnCorrectPriceWithOneIngredient() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredientFirst.getPrice()).thenReturn(50f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientFirst);

        float result = burger.getPrice();

        assertEquals("Цена должна быть 250 (100*2 + 50)", 250f, result, 0.001f);
    }

    @Test
    public void getPriceShouldReturnCorrectPriceWithManyIngredients() {
        when(mockBun.getPrice()).thenReturn(100f);

        Ingredient[] ingredients = new Ingredient[5];
        float[] prices = {10f, 20f, 30f, 40f, 50f};
        float expectedSum = 0f;

        for (int i = 0; i < ingredients.length; i++) {
            ingredients[i] = mock(Ingredient.class);
            when(ingredients[i].getPrice()).thenReturn(prices[i]);
            expectedSum += prices[i];
            burger.addIngredient(ingredients[i]);
        }

        burger.setBuns(mockBun);

        float result = burger.getPrice();

        float expected = 200f + expectedSum;
        assertEquals("Цена должна быть " + expected, expected, result, 0.001f);
    }
}
