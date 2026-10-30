package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тесты enum IngredientType")
public class IngredientTypeTest {

    @Test
    @DisplayName("Значения enum совпадают с ожидаемыми")
    public void enumValuesAreCorrect() {
        IngredientType[] values = IngredientType.values();

        assertEquals(2, values.length);
        assertEquals(IngredientType.SAUCE, values[0]);
        assertEquals(IngredientType.FILLING, values[1]);
    }

    @Test
    @DisplayName("valueOf возвращает значение по имени")
    public void valueOfReturnsCorrectValue() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    @DisplayName("valueOf бросает исключение для неизвестного имени")
    public void valueOfThrowsForUnknownName() {
        assertThrows(IllegalArgumentException.class,
                () -> IngredientType.valueOf("UNKNOWN"));
    }
}
