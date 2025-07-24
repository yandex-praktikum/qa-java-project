package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void shouldHaveSauceValue() {
        assertNotNull(IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void shouldHaveFillingValue() {
        assertNotNull(IngredientType.valueOf("FILLING"));
    }
}
