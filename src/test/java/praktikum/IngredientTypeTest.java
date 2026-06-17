package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IngredientTypeTest {

    @Test
    @DisplayName("Проверка, что IngredientType содержит SAUCE")
    public void testIngredientTypeContainsSauce() {
        Assertions.assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    @DisplayName("Проверка, что IngredientType содержит FILLING")
    public void testIngredientTypeContainsFilling() {
        Assertions.assertEquals("FILLING", IngredientType.FILLING.name());
    }

    @Test
    @DisplayName("Проверка, что IngredientType имеет ровно 2 значения")
    public void testIngredientTypeHasTwoValues() {
        IngredientType[] values = IngredientType.values();
        Assertions.assertEquals(2, values.length);
    }
}