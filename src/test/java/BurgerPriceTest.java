import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    @Parameterized.Parameter
    public float bunPrice;

    @Parameterized.Parameter(1)
    public float[] ingredientPrices;

    @Parameterized.Parameter(2)
    public float expectedPrice;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {100f, new float[]{}, 200f},
                {100f, new float[]{50f}, 250f},
                {100f, new float[]{50f, 30f}, 280f},
                {100f, new float[]{50f, 100f}, 350f}
        });
    }
    @Test
    public void getPriceReturnsCorrectPrice(){

        Burger burger = new Burger();
        Bun bun = new Bun("bun", bunPrice);

        burger.setBuns(bun);

        for (float price: ingredientPrices) {
            Ingredient ing = new Ingredient(IngredientType.SAUCE, "ing", price);
            burger.addIngredient(ing);
        }
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }
}