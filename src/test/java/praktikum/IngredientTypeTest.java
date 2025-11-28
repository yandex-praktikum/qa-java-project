package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void sauceTypeIsCorrect() {
        assertEquals("Тип SAUCE должен быть доступен", IngredientType.SAUCE, IngredientType.SAUCE);
    }

    @Test
    public void fillingTypeIsCorrect() {
        assertEquals("Тип FILLING должен быть доступен", IngredientType.FILLING, IngredientType.FILLING);
    }
}