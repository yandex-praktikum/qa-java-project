import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IngredientTypeTest {

    @Test
    void testContainExpectedConstants() {
        assertNotNull(IngredientType.SAUCE);
        assertNotNull(IngredientType.FILLING);
    }

    @Test
    void testHaveCorrectEnumValues() {
        IngredientType[] values = IngredientType.values();
        assertEquals(2, values.length);
        assertEquals(IngredientType.SAUCE, values[0]);
        assertEquals(IngredientType.FILLING, values[1]);
    }
}
