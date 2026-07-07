import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class IngredientTypeTest {

    @Test
    void shouldContainAllIngredientTypes() {
        assertArrayEquals(
                new IngredientType[]{
                        IngredientType.SAUCE,
                        IngredientType.FILLING
                },
                IngredientType.values()
        );
    }
}