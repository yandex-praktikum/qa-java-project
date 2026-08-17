import praktikum.IngredientType;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void testValuesReturnsCorrectCount() {
        IngredientType[] values = IngredientType.values();
        assertEquals(2, values.length);
    }

    @Test
    public void testSauceNameIsCorrect() {
        assertEquals("SAUCE", IngredientType.SAUCE.name());
    }
    @Test
    public void testFillingNameIsCorrect() {
        assertEquals("FILLING", IngredientType.FILLING.name());
    }
    @Test
    public void testValueOfSauceReturnsSauce() {
        IngredientType result = IngredientType.valueOf("SAUCE");
        assertEquals(IngredientType.SAUCE, result);
    }
    @Test
    public void testValueOfFillingReturnsFilling() {
        IngredientType result = IngredientType.valueOf("FILLING");
        assertEquals(IngredientType.FILLING, result);
    }
}