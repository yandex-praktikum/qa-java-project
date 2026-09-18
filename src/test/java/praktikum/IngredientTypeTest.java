package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTypeTest {
    @Test
    @DisplayName("Проверка наличия всех значений IngredientType")
    public void checkAllIngredientTypesSuccess() {
        IngredientType[] values = IngredientType.values();
        assertEquals(2, values.length, "В ENUM IngredientType должно быть 2 значения");
        assertEquals(IngredientType.SAUCE, values[0], "Первое значение должно быть SAUCE");
        assertEquals(IngredientType.FILLING, values[1], "Второе значение должно быть FILLING");
    }

}
