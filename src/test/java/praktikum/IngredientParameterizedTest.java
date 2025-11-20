package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientParameterizedTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "type={0}, name={1}, price={2}")
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.SAUCE, "ketchup", 10.0f},
                {IngredientType.SAUCE, "mustard", 0.0f},
                {IngredientType.FILLING, "cutlet", 150.5f},
                {IngredientType.FILLING, "cheese", 50.0f}
        };
    }

    @Test
    public void getTypeReturnsSameTypeAsInConstructor() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }

    @Test
    public void getNameReturnsSameNameAsInConstructor() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void getPriceReturnsSamePriceAsInConstructor() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.0001f);
    }
}