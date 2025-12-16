package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void enumContainsSauceAndFilling() {
        assertNotNull(IngredientType.valueOf("SAUCE"));
        assertNotNull(IngredientType.valueOf("FILLING"));
    }

    @Test
    public void enumValuesCountIsTwo() {
        assertEquals(2, IngredientType.values().length);
    }
}
