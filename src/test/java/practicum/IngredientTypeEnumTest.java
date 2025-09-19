package practicum;
import org.junit.Test;
import ru.practicum.IngredientType;

import static org.junit.Assert.*;

import java.util.EnumSet;


public class IngredientTypeEnumTest {
    // Тест проверки всех значений перечисления
    @Test
    public void testEnumValues() {
        // Получаем все значения перечисления
        IngredientType[] types = IngredientType.values();

        // Проверяем, что в перечислении два значения
        assertEquals(2, types.length); // Должно быть два значения

        // Проверяем, что перечисление содержит нужные элементы
        assertTrue(EnumSet.allOf(IngredientType.class).contains(IngredientType.SAUCE));
        assertTrue(EnumSet.allOf(IngredientType.class).contains(IngredientType.FILLING));
    }
}