package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void sauceShouldHaveCorrectName() {
        assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    public void fillingShouldHaveCorrectName() {
        assertEquals("FILLING", IngredientType.FILLING.name());
    }
}