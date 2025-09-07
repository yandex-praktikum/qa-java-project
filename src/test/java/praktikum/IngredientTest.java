package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class IngredientTest {

    @Test
    public void getPrice() {
        float expectedPrice = 12.3f;
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE,"тестовый",12.3f);
        Assert.assertEquals(expectedPrice,ingredient.getPrice(),0);
    }

    @Test
    public void getName() {
        String expectedName = "тест";
        Ingredient ingredient = new Ingredient(IngredientType.FILLING,"тест",0.3f);
        Assert.assertEquals(expectedName,ingredient.getName());
    }

    @Test
    public void getType() {
        IngredientType expected = IngredientType.SAUCE;
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE,"тест",0.3f);
        Assert.assertEquals(expected,ingredient.getType());

    }
}