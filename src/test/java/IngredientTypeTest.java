import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class IngredientTypeTest {

    @Test
    public void ingredientTypeHasTwoValuesSauceAndFilling() {
        IngredientType[] values = IngredientType.values();
        assertEquals(2, values.length);
        assertArrayEquals(
                new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING},
                values
        );
    }

    @Test
    public void valueOfReturnsCorrectEnumConstant() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
