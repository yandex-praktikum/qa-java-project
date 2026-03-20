package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTypeTest {

    @Test
    void enumShouldContainTwoValues() {
        assertEquals(2, IngredientType.values().length);
    }

    @Test
    void valueOfShouldReturnSauce() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    void valueOfShouldReturnFilling() {
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}