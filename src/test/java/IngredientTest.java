import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

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

    @Parameterized.Parameters(name = "{index}: type={0}, name={1}, price={2}")
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "ketchup", 10f},
                {IngredientType.FILLING, "cutlet", 100.5f},
                {IngredientType.SAUCE, "майонез", 0f}
        });
    }

    @Test
    public void ingredientStoresNameCorrectly() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void ingredientStoresPriceCorrectly() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.0001f);
    }

    @Test
    public void ingredientStoresTypeCorrectly() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }
}
