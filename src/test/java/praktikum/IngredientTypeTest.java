package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTypeTest {

    @Test
    void values() {
        assertEquals(2, IngredientType.values().length);
        assertEquals(IngredientType.SAUCE, IngredientType.values()[0]);
        assertEquals(IngredientType.FILLING, IngredientType.values()[1]);
    }

    @Test
    void valueOf() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}