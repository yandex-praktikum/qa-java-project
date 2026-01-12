package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;
    private final Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.ingredient = new Ingredient(type, name, price);
    }

    @Parameterized.Parameters(name = "{0}: {1}, price: {2}")
    public static Object[][] ingredientData() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.FILLING, "cutlet", 100.0f},
                {IngredientType.SAUCE, "sour cream", 200.0f}
        };
    }

    @Test
    public void testGetName() {
        assertEquals("Название ингредиента не совпадает", name, ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals("Цена ингредиента не совпадает", price, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void testGetType() {
        assertEquals("Тип ингредиента не совпадает", type, ingredient.getType());
    }
}
