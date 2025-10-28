import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTest {

    @Parameters(name = "Test {index}: type={0}, name={1}, price={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { IngredientType.SAUCE, "Hot sauce", 100.0f },
                { IngredientType.FILLING, "Cheese", 50.0f },
                { IngredientType.SAUCE, "Sour cream", 75.0f },
                { IngredientType.FILLING, "Lettuce", 25.0f }
        });
    }

    @Parameter()
    public IngredientType type;

    @Parameter(1)
    public String name;

    @Parameter(2)
    public float price;

    @Test
    public void testConstructor() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.type);
        assertEquals(name, ingredient.name);
        assertEquals(price, ingredient.price, 0.001);
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.001);
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }
}
