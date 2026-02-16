package praktikum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final float bunPrice;
    private final float[] ingredientsPrices;
    private final float expectedTotalPrice;

    public BurgerParameterizedTest(float bunPrice, float[] ingredientsPrices, float expectedTotalPrice) {
        this.bunPrice = bunPrice;
        this.ingredientsPrices = ingredientsPrices;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Parameterized.Parameters(name="Test with bun price={0}, ingred prices={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {200.0F, new float[]{80.0F, 45.0F, 45.0F}, 570.0F},
                {150.0F, new float[]{60.0F, 30.0F}, 420.0F},
                {100.0F, new float[]{20.0F, 15.0F, 10.0F}, 265.0F},
                {300.0F, new float[]{}, 600.0F}
        });
    }

    @Test
    public void testPriceWithIngredients() throws Exception {

        Burger burger = new Burger();

        Bun mockedBun = mock(Bun.class);
        when(mockedBun.getPrice()).thenReturn(bunPrice);

        Method setBunMethod = Burger.class.getDeclaredMethod("setBuns", Bun.class);
        setBunMethod.setAccessible(true);
        setBunMethod.invoke(burger, mockedBun);

        for (float price : ingredientsPrices) {
            Ingredient mockIngredient = mock(Ingredient.class);
            when(mockIngredient.getPrice()).thenReturn(price);
            burger.addIngredient(mockIngredient);
        }

        float totalExpectedPrice = bunPrice * 2 + sumArray(ingredientsPrices);

        assertEquals("Общая цена рассчитана неверно", totalExpectedPrice, burger.getPrice(), 0.0F);
    }

    private float sumArray(float[] array) {
        float result = 0;
        for (float f : array) {
            result += f;
        }
        return result;
    }
}