package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private static final float DELTA = 0.001f;

    private static final String BUN_NAME = "black bun";
    private static final String INGREDIENT_NAME = "cutlet";

    private final float bunPrice;
    private final float ingredientPrice;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(float bunPrice, float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 250f},
                {0f, 0f, 0f},
                {200f, 100f, 500f},
                {150f, 75f, 375f}
        });
    }

    @Test
    public void getPriceShouldReturnCorrectPriceForBunAndOneIngredient() {
        Bun bun = new Bun(BUN_NAME, bunPrice);
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, INGREDIENT_NAME, ingredientPrice);
        Burger burger = new Burger();

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, DELTA);
    }
}