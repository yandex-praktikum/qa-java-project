package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerPriceParamTest {
    private final float bunPrice;
    private final List<Float> ingredientPrice;
    private final float expectedPrice;

    public BurgerPriceParamTest(float bunPrice, List<Float> ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "bun = {0}, ingredients = {1}, expected = {2}")
    public static Object[][] data() {
        return new Object[][]{
                {100f, Arrays.asList(30f, 50f), 280f},
                {0f, Arrays.asList(), 0f},
                {25.5f, Arrays.asList(4.5f, 5.5f), 61f}
        };
    }

    @Test
    public void getPriceCalculateCorrectly() {
        Burger burger = new Burger();

        burger.setBuns(new Bun("bun", bunPrice));

        for (Float price : ingredientPrice) {
            burger.addIngredient(new Ingredient(IngredientType.SAUCE, "ing", price));
        }

        float actual = burger.getPrice();
        assertEquals(expectedPrice, actual, 0.0001f);
    }
}
