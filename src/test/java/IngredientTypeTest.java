import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

        @Test
        void ingredientSauceTest() {
            assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));

        }

        @Test
        void ingredientFillingTest() {
            assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
