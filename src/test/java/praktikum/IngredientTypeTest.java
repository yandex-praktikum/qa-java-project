package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTypeTest {

    @Test
    void valuesLengthTest() {
        IngredientType[] values = IngredientType.values();
        assertEquals(2, values.length);
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void valueOfExistTest(IngredientType type) {
        assertEquals(type, IngredientType.valueOf(type.name()));
    }
}