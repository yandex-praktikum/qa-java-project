package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IngredientTypeTest {

    @Test
    public void valuesReturnsAllIngredientTypes() {
        IngredientType[] expectedTypes = {
                IngredientType.SAUCE,
                IngredientType.FILLING
        };

        IngredientType[] actualTypes = IngredientType.values();

        assertArrayEquals(expectedTypes, actualTypes);
    }
}