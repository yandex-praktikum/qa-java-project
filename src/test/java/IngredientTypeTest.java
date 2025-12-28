import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

        @Test
        void ingredientTypeTest() {
            assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
            assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
        }
}
