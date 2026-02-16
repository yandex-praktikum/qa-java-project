package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTypeTest {

    @Test
    @DisplayName("Enum содержит все ожидаемые значения")
    void testEnum_ContainAllExpectedValues() {

        IngredientType[] values = IngredientType.values();

        assertEquals(2, values.length, "Должно быть 2 типа ингредиентов");

        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

}