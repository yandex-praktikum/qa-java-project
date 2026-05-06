package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void enum_HasTwoValues() {
        assertEquals(2, IngredientType.values().length);
    }

    @Test
    public void enum_ContainsSauce() {
        assertNotNull(IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void enum_ContainsFilling() {
        assertNotNull(IngredientType.valueOf("FILLING"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}