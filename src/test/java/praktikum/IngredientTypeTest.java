package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

    @Test
    public void sauceValueExists() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void fillingValueExists() {
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
