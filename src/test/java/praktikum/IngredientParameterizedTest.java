package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {

    @Parameterized.Parameters(name = "type={0}, name={1}, price={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.SAUCE, "sour cream", 0f},
                {IngredientType.FILLING, "cutlet", 250.5f},
        });
    }

    @Parameterized.Parameter(0)
    public IngredientType type;

    @Parameterized.Parameter(1)
    public String name;

    @Parameterized.Parameter(2)
    public float price;

    @Test
    public void getType_returnsConstructorType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }

    @Test
    public void getName_returnsConstructorName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void getPrice_returnsConstructorPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.0001f);
    }
}


