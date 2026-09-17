package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    private static final String INGREDIENT_NAME = "hot sauce";
    private static final float INGREDIENT_PRICE = 100f;
    private static final float DELTA = 0.001f;

    private Ingredient ingredient;

    @Before
    public void setUp() {
        ingredient = new Ingredient(IngredientType.SAUCE, INGREDIENT_NAME, INGREDIENT_PRICE);
    }

    @Test
    public void getTypeShouldReturnCorrectType() {
        assertEquals(IngredientType.SAUCE, ingredient.getType());
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        assertEquals(INGREDIENT_NAME, ingredient.getName());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        assertEquals(INGREDIENT_PRICE, ingredient.getPrice(), DELTA);
    }
}