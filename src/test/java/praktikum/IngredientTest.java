package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
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
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.SAUCE, "sour cream", 200.0f},
                {IngredientType.FILLING, "cutlet", 100.0f},
                {IngredientType.FILLING, "dinosaur", 200.0f}
        });
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Type should match constructor parameter", type, ingredient.getType());
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Name should match constructor parameter", name, ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Price should match constructor parameter", price, ingredient.getPrice(), 0.001);
    }
    @Test
    public void testIngredientConstructorAndGetters() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "test sauce", 123.45f);

        assertEquals(IngredientType.SAUCE, ingredient.getType());
        assertEquals("test sauce", ingredient.getName());
        assertEquals(123.45f, ingredient.getPrice(), 0.001);
    }

    @Test
    public void testIngredientWithSpecialCharacters() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "filling_123@test", 888.88f);

        assertEquals(IngredientType.FILLING, ingredient.getType());
        assertEquals("filling_123@test", ingredient.getName());
        assertEquals(888.88f, ingredient.getPrice(), 0.001);
    }
}