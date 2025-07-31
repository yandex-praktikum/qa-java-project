package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IngredientTests {
    private final String NAME = "Spicy-X";
    private final float PRICE = 80.00F;
    private Ingredient ingredient;

    @Before
    public void setUp() {
        ingredient = new Ingredient(IngredientType.SAUCE, NAME, PRICE);
    }

    @Test
    public void getPriceTest() {
        Assert.assertEquals(PRICE, ingredient.getPrice(), 0);
    }

    @Test
    public void getNameTest() {
        Assert.assertEquals(NAME, ingredient.getName());
    }

    @Test
    public void getTypeTest() {
        Assert.assertEquals(IngredientType.SAUCE, ingredient.getType());
    }
}
