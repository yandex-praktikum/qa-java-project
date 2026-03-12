import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

    @Test
    void sauceTypeTest() {

        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    void fillingTypeTest() {

        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
