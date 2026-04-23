package praktikum;

import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

public class IngredientTest {

    // Тест: проверка метода getPrice()
    @Test
    public void testIngredientGetPrice() {

        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Saratov", 164.0F);

        assertEquals(164.0F, ingredient.getPrice(), 0);
    }

    // Тест: проверка метода getName()
    @Test
    public void testIngredientGetName() {

        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Saratov", 164.0F);

        assertEquals("Saratov", ingredient.getName());
    }

    // Тест: проверка метода getType()
    @Test
    public void testIngredientGetType() {

        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Saratov", 164.0F);

        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}
