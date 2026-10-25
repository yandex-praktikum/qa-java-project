package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DatabaseIngredientsTest {

    @Parameters(name = "Ингредиент #{0}: {1} {2}, {3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, IngredientType.SAUCE,   "hot sauce",   100f},
                {1, IngredientType.SAUCE,   "sour cream",  200f},
                {2, IngredientType.SAUCE,   "chili sauce", 300f},
                {3, IngredientType.FILLING, "cutlet",      100f},
                {4, IngredientType.FILLING, "dinosaur",    200f},
                {5, IngredientType.FILLING, "sausage",     300f}
        });
    }

    private final int index;
    private final IngredientType type;
    private final String name;
    private final float price;

    public DatabaseIngredientsTest(int index, IngredientType type, String name, float price) {
        this.index = index;
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Test
    public void availableIngredientsReturnsSixItems() {
        List<Ingredient> ingredients = new Database().availableIngredients();

        assertEquals(6, ingredients.size());
    }

    @Test
    public void ingredientAtIndexHasExpectedType() {
        List<Ingredient> ingredients = new Database().availableIngredients();

        assertEquals(type, ingredients.get(index).getType());
    }

    @Test
    public void ingredientAtIndexHasExpectedName() {
        List<Ingredient> ingredients = new Database().availableIngredients();

        assertEquals(name, ingredients.get(index).getName());
    }

    @Test
    public void ingredientAtIndexHasExpectedPrice() {
        List<Ingredient> ingredients = new Database().availableIngredients();

        assertEquals(price, ingredients.get(index).getPrice(), 0.001f);
    }
}