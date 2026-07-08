package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PriceParameterizedTest {

    private float bunPrice;
    private Ingredient[] ingredients;
    private float expectedPrice;

    public PriceParameterizedTest(float bunPrice, Ingredient[] ingredients, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> GetPrice() {
        return Arrays.asList(new Object[][]{
                {10.0f, new Ingredient[]{new Ingredient(IngredientType.FILLING, "Салат", 50.0f)}, 70.0f},
                {10.0f, new Ingredient[]{
                        new Ingredient(IngredientType.SAUCE, "Сырный", 20.0f)
                }, 40.0f}
        });
    }

    @Test
    public void testGetPrice() {
        Burger testBurger = new Burger();
        Bun bun = new Bun("Булка1", bunPrice);
        testBurger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            testBurger.addIngredient(ingredient);
        }

        assertEquals(expectedPrice, testBurger.getPrice(), 0.001f);
    }
}
