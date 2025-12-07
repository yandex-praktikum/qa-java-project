package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class BurgerGetPriceParameterizedTest {

    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expected;

    public BurgerGetPriceParameterizedTest(float bunPrice, float[] ingredientPrices, float expected) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "Bun price: {0}, Ingredients: {1}, Expected: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, new float[]{}, 200f},
                {100f, new float[]{100f}, 300f},
                {200f, new float[]{100f, 200f}, 700f},
                {300f, new float[]{300f, 300f, 100f}, 1300f},
        });
    }

    @Test
    public void testGetPrice() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        when(bun.getName()).thenReturn("stub bun");
        burger.setBuns(bun);

        for (float ingredientPrice : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(ingredientPrice);
            when(ingredient.getName()).thenReturn("ingredient");
            when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
            burger.addIngredient(ingredient);
        }

        assertEquals((int) expected, (int) burger.getPrice());
    }
}
