package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTests {
    public static final String SAUCE = "SAUCE";
    public static final String FILLING = "FILLING";

    @Test
    public void sauceTest() {
        assertEquals(SAUCE, IngredientType.SAUCE.toString());
    }

    @Test
    public void fillingTest() {
        assertEquals(FILLING, IngredientType.FILLING.toString());
    }
}
