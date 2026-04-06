import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTest {



    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ketchup", 150f);
        float currentPrice = ingredient.getPrice();
        assertEquals(150f, currentPrice, 0.01);

    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "meatball", 75f);
        String currentName = ingredient.getName();
        assertEquals("meatball", currentName);
    }

    @Test
    public void getIngredientTypeTest() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "mustard", 50f);
        IngredientType currentType = ingredient.getType();
        assertEquals(IngredientType.SAUCE, currentType);
    }
}
