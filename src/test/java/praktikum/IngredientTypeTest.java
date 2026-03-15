package praktikum;

import constants.TestMessages;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IngredientTypeTest {
    @Test
    void enumHasTwoValues() {
        IngredientType[] types = IngredientType.values();
        assertEquals(2, types.length, TestMessages.ERROR);
        assertTrue(contains(types, IngredientType.SAUCE), TestMessages.ERROR);
        assertTrue(contains(types, IngredientType.FILLING), TestMessages.ERROR);
    }

    private boolean contains(IngredientType[] types, IngredientType target) {
        for (IngredientType type : types) {
            if (type == target) return true;
        }
        return false;
    }
}