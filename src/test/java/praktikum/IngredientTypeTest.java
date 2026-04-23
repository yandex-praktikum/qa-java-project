package praktikum;

import org.junit.Test;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    // Тест: Проверка количества значений в enum и корректность работы valueOf()
    @Test
    public void testIngredientTypeValues() {
        // В IngredientType ровно 2 значения (SAUCE и FILLING)
        assertEquals(2, IngredientType.values().length);

        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));

        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    // Тест: Проверка метода name()
    @Test
    public void testIngredientTypeNames() {

        assertEquals("SAUCE", IngredientType.SAUCE.name());

        assertEquals("FILLING", IngredientType.FILLING.name());
    }

    // Тест: Проверка метода ordinal()
    @Test
    public void testIngredientTypeOrdinal() {

        assertEquals(0, IngredientType.SAUCE.ordinal());

        assertEquals(1, IngredientType.FILLING.ordinal());
    }
}
