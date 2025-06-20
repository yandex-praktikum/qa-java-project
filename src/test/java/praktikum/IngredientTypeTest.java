package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void ingredientTypeSauceExistsTest() {
        assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    public void ingredientTypeFillingExistsTest() {
        assertEquals("FILLING", IngredientType.FILLING.name());
    }
}