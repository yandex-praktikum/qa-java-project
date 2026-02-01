package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {

    @Test
    public void testEnumValues() {
        assertNotNull(IngredientType.valueOf("SAUCE"));
        assertNotNull(IngredientType.valueOf("FILLING"));
        assertEquals(2, IngredientType.values().length);
    }
}
