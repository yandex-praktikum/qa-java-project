import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientTypeTests {

    @Test
    @DisplayName("Перечисление IngredientType содержит SAUCE и FILLING")
    public void enumContainsSauceAndFilling() {
        IngredientType[] values = IngredientType.values();

        assertTrue(
                java.util.Arrays.asList(values).contains(IngredientType.SAUCE),
                "Перечисление должно содержать SAUCE"
        );
        assertTrue(
                java.util.Arrays.asList(values).contains(IngredientType.FILLING),
                "Перечисление должно содержать FILLING"
        );
    }

    @Test
    @DisplayName("Метод toString() возвращает корректное название типа ингредиента")
    public void toStringReturnsName() {
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }
}
