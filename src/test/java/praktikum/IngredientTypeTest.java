package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void values_containsSauceAndFilling() {
        IngredientType[] values = IngredientType.values();
        assertArrayEquals(new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING}, values);
    }

    @Test
    public void toString_returnsName() {
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }
}


