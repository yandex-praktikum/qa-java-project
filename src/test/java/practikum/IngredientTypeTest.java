package practikum;

import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertArrayEquals;

public class IngredientTypeTest {

    @Test
    public void ingredientTypeValuesCorrect() {
        IngredientType[] types = IngredientType.values();
        assertArrayEquals(new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING}, types);
    }
}
