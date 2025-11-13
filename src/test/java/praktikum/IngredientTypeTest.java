package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void testValues() {
        assertEquals(2, IngredientType.values().length);
        assertSame(IngredientType.values()[0], IngredientType.SAUCE);
        assertSame(IngredientType.values()[1], IngredientType.FILLING);
    }
}