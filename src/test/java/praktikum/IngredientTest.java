package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    @Parameterized.Parameters(name = "type={0}, name={1}, price={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "ketchup", 10f},
                {IngredientType.SAUCE, "mustard", 0.01f},
                {IngredientType.FILLING, "cutlet", 50f},
                {IngredientType.FILLING, "cheese", 33.3f}
        });
    }

    @Parameterized.Parameter(0)
    public IngredientType type;

    @Parameterized.Parameter(1)
    public String name;

    @Parameterized.Parameter(2)
    public float price;

    @Test
    public void ingredientGettersReturnConstructorParameters() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice(), 0.0001f);
    }
}
