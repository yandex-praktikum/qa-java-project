package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {
    @Test
    void ingredientTypeEnumValueCorrect() {
        IngredientType[] expectedValues = {IngredientType.SAUCE, IngredientType.FILLING};
        IngredientType[] actualValues = IngredientType.values();

        assertEquals(expectedValues.length, actualValues.length);
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], actualValues[i]);
        }
    }
}
