package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IngredientTypeTest {

    @Test
    void valuesContainsExactlyTwoTypes() {
        assertEquals(2, IngredientType.values().length);
    }

    @ParameterizedTest
    @ValueSource(strings = {"SAUCE", "FILLING"})
    void valueOfReturnsExpectedEnumConstant(String typeName) {
        assertNotNull(IngredientType.valueOf(typeName));
    }
}