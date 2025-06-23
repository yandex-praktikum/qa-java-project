package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {

    @Test
    public void testHaveSauceType() {
        assertNotNull(IngredientType.SAUCE);
    }

    @Test
    public void testHaveFillingType() {
        assertNotNull(IngredientType.FILLING);
    }
}
