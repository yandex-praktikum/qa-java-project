package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTests {
    private Ingredient ingredient;
    private String ingredientName;
    private float ingredientPrice;
    private IngredientType type;

    public IngredientTests(IngredientType type, String ingredientName, float ingredientPrice) {
        this.type = type;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }
    @Parameterized.Parameters
    public static Object[][] ingredientData() {
        return new Object[][]{
                {SAUCE, "space sauce", 80},
                {SAUCE, "spicy sauce", 90},
                {FILLING, "magnolia", 100},
                {FILLING, "steak", 1000}
        };
    }
    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(type, ingredientName, ingredientPrice);
        assertEquals(ingredientPrice, ingredient.getPrice(), 0);
    }
    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(type, ingredientName, ingredientPrice);
        assertEquals(ingredientName, ingredient.getName());
    }
    @Test
    public void getTypeTest() {
        Ingredient ingredient = new Ingredient(type, ingredientName, ingredientPrice);
        assertEquals(type, ingredient.getType());
    }

}
