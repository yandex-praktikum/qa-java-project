package praktikum;

import org.junit.Assert;
import org.junit.Test;

import static helper.Constants.DELTA_FLOAT_BOUNED;

public class IngredientTest {

    private final Database database = new Database();
    private final IngredientType firstIngredientType = database.availableIngredients().get(0).type;
    private final String firstIngredientName = database.availableIngredients().get(0).name;
    private final float firstIngredientPrice = database.availableIngredients().get(0).price;

    @Test
    public void getNameReturnIngredientNameTest() {
        Ingredient ingredient = new Ingredient(firstIngredientType, firstIngredientName, firstIngredientPrice);
        Assert.assertEquals(firstIngredientName, ingredient.getName());
    }

    @Test
    public void getPriceReturnIngredientPriceTest() {
        Ingredient ingredient = new Ingredient(firstIngredientType, firstIngredientName, firstIngredientPrice);
        Assert.assertEquals(firstIngredientPrice, ingredient.getPrice(), DELTA_FLOAT_BOUNED);
    }

    @Test
    public void getTypeReturnIngredientTypeTest() {
        Ingredient ingredient = new Ingredient(firstIngredientType, firstIngredientName, firstIngredientPrice);
        Assert.assertEquals(firstIngredientType, ingredient.getType());
    }

}
