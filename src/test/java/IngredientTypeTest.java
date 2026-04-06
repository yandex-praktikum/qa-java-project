import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {
    @Test
    public void sauceTypeTest() {

        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void fillingTypeTest() {

        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
