import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {
    @Test
    @DisplayName("Проверка значений Enum")
    void testIngredientTypeValues() {
        assertEquals(2, IngredientType.values().length);
        assertEquals("SAUCE", IngredientType.SAUCE.name());
        assertEquals("FILLING", IngredientType.FILLING.name());
    }
}
