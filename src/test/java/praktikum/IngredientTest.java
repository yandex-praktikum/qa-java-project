package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final String name;
    private final float price;
    private final IngredientType type;
    private Ingredient ingredient;

    public IngredientTest(String name, float price, IngredientType type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Соус", 50.0f, IngredientType.SAUCE},
                {"Котлета", 100.0f, IngredientType.FILLING},
                {"Томат", 30.0f, IngredientType.FILLING}
        };
    }

    @Before
    public void setUp() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void testReturnCorrectNameFromGetter() {
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testReturnCorrectPriceFromGetter() {
        assertEquals(price, ingredient.getPrice(), 0.0f);
    }

    @Test
    public void testReturnCorrectTypeFromGetter() {
        assertEquals(type, ingredient.getType());
    }
}
