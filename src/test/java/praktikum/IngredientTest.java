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

    @Parameterized.Parameters(name = "Тип: {0}, название: {1}, цена: {2}")
    public static Object[][] getData() {
        return new Object[][] {
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.FILLING, "cutlet", 150.5f}
        };
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.001f);
    }
}
