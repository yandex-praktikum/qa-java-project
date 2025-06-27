package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTypeTest {

    @Test
    public void testEnumLength() {
        assertEquals(2, IngredientType.values().length);
    }

    @Test
    public void testSauceName() {
        assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    public void testFillingName() {
        assertEquals("FILLING", IngredientType.FILLING.name());
    }
}
