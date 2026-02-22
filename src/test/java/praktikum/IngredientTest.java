package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void testSauceIngredient() {
        IngredientType expectedType = IngredientType.SAUCE;
        String expectedName = "hot sauce";
        float expectedPrice = 100f;

        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

        assertEquals(expectedType, ingredient.getType());
        assertEquals(expectedName, ingredient.getName());
        assertEquals(expectedPrice, ingredient.getPrice(), 0.001);

        // Проверка публичных полей
        assertEquals(expectedType, ingredient.type);
        assertEquals(expectedName, ingredient.name);
        assertEquals(expectedPrice, ingredient.price, 0.001);
    }

    @Test
    public void testFillingIngredient() {
        IngredientType expectedType = IngredientType.FILLING;
        String expectedName = "cutlet";
        float expectedPrice = 100f;

        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

        assertEquals(expectedType, ingredient.getType());
        assertEquals(expectedName, ingredient.getName());
        assertEquals(expectedPrice, ingredient.getPrice(), 0.001);
    }

    @Test
    public void testIngredientWithDifferentPrices() {
        Ingredient cheap = new Ingredient(IngredientType.SAUCE, "sauce", 50f);
        Ingredient expensive = new Ingredient(IngredientType.FILLING, "meat", 200f);

        assertEquals(50f, cheap.getPrice(), 0.001);
        assertEquals(200f, expensive.getPrice(), 0.001);
    }
}