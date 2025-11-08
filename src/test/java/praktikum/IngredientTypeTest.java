package praktikum;

import junit.framework.TestCase;

import static org.junit.Assert.assertArrayEquals;

public class IngredientTypeTest extends TestCase {

    public void testValues() {
        IngredientType[] expected = {IngredientType.SAUCE, IngredientType.FILLING};
        assertArrayEquals(expected, IngredientType.values());
    }
}