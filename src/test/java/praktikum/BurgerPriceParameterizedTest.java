package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

/**
 * Parameterized tests for price calculation.
 * Tests different ingredient combinations.
 */
@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest extends BaseBurgerTest {

    @Parameterized.Parameters(name = "Test {index}: ingredients={0}, price={1}")
    public static Collection<Object[]> getPriceTestData() {
        return Arrays.asList(new Object[][] {
                // Number of ingredients, expected price
                {0, 200.0f},   // Bun only (100 * 2)
                {1, 300.0f},   // Bun + cutlet (200 + 100)
                {2, 500.0f},   // Bun + cutlet + dinosaur (200 + 100 + 200)
                {3, 800.0f},   // Bun + cutlet + dinosaur + sausage (200 + 100 + 200 + 300)
                {4, 1000.0f},  // All fillings (200 + 100 + 200 + 300 + 200 for sauce)
        });
    }

    @Parameterized.Parameter
    public int ingredientCount;

    @Parameterized.Parameter(1)
    public float expectedPrice;

    @Test
    public void testGetPriceWithDifferentIngredientCount() {
        burger.setBuns(blackBun);

        // Add ingredients in specific order
        if (ingredientCount >= 1) burger.addIngredient(cutlet);
        if (ingredientCount >= 2) burger.addIngredient(dinosaur);
        if (ingredientCount >= 3) burger.addIngredient(sausage);
        if (ingredientCount >= 4) burger.addIngredient(sourCream);

        assertEquals(String.format("Wrong price for %d ingredients", ingredientCount),
                expectedPrice, burger.getPrice(), 0.001f);
    }
}