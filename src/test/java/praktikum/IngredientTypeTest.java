package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void values_ShouldReturnCorrectNumberOfValues() {
        IngredientType[] values = IngredientType.values();
        assertEquals(2, values.length);
    }

    @Test
    public void values_ShouldContainSauceAsFirstValue() {
        IngredientType[] values = IngredientType.values();
        assertEquals(IngredientType.SAUCE, values[0]);
    }

    @Test
    public void values_ShouldContainFillingAsSecondValue() {
        IngredientType[] values = IngredientType.values();
        assertEquals(IngredientType.FILLING, values[1]);
    }

    @Test
    public void valueOf_ShouldReturnSauceForSauceString() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void valueOf_ShouldReturnFillingForFillingString() {
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    public void toString_ShouldReturnCorrectStringForSauce() {
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
    }

    @Test
    public void toString_ShouldReturnCorrectStringForFilling() {
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }

    @Test
    public void ordinal_ShouldReturnZeroForSauce() {
        assertEquals(0, IngredientType.SAUCE.ordinal());
    }

    @Test
    public void ordinal_ShouldReturnOneForFilling() {
        assertEquals(1, IngredientType.FILLING.ordinal());
    }
}