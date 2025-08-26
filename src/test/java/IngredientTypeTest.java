import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTypeTest {

    @Test
    void testEnumValues() {
        IngredientType sauce = IngredientType.SAUCE;
        IngredientType filling = IngredientType.FILLING;

        assertEquals("SAUCE", sauce.name());
        assertEquals("FILLING", filling.name());

        IngredientType[] values = IngredientType.values();
        assertArrayEquals(new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING}, values);
    }
}

