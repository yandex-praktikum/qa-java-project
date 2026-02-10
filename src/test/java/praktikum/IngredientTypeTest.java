package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

    @Test
    public void valueOf() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }
}

