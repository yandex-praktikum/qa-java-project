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
    public static Object[][] getIngredientData() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.FILLING, "cutlet", 300f}
        };
    }

    @Test
    public void getTypeShouldReturnCorrectType() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(name, ingredient.getName());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(price, ingredient.getPrice(), 0);
    }
}