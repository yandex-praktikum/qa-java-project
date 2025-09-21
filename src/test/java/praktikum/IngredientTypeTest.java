package praktikum;

import org.junit.Assert;
import org.junit.Test;

public class IngredientTypeTest {

    @Test
    public void ingredientTypeSauceExists() {
        Assert.assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    public void ingredientTypeFillingExists() {
        Assert.assertEquals("FILLING", IngredientType.FILLING.name());
    }
}
