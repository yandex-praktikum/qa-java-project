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

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.SAUCE, "sour cream", 200f},
                {IngredientType.SAUCE, "chili sauce", 300f},
                {IngredientType.FILLING, "cutlet", 100f},
                {IngredientType.FILLING, "dinosaur", 200f},
                {IngredientType.FILLING, "sausage", 300f},
                {IngredientType.SAUCE, "", 0f},
                {IngredientType.FILLING, null, 0f}
        };
    }

    @Test
    public void testIngredientConstructorAndGetters() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals("Ingredient type should match constructor parameter", type, ingredient.getType());
        assertEquals("Ingredient name should match constructor parameter", name, ingredient.getName());
        assertEquals("Ingredient price should match constructor parameter", price, ingredient.getPrice(), 0.001);
    }

    @Test
    public void testIngredientFieldsAreAccessible() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals("Ingredient type field should be accessible", type, ingredient.type);
        assertEquals("Ingredient name field should be accessible", name, ingredient.name);
        assertEquals("Ingredient price field should be accessible", price, ingredient.price, 0.001);
    }
}