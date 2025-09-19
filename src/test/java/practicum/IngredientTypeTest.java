package practicum;

import org.junit.Test;
import ru.practicum.IngredientType;

import java.util.EnumSet;

import static org.junit.Assert.*;

public class IngredientTypeTest {

    @Test
    public void testInvalidEnumValue() {
        System.out.println("Тестирование обработки неверного значения перечисления");
        try {
            IngredientType.valueOf("INVALID");
            fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Корректно обработано неверное значение перечисления");
        }
    }

    @Test
    public void testEqualityAndInequality() {
        System.out.println("Тестирование равенства и неравенства элементов перечисления");
        assertEquals(IngredientType.SAUCE, IngredientType.SAUCE);
        assertEquals(IngredientType.FILLING, IngredientType.FILLING);
        assertNotEquals(IngredientType.SAUCE, IngredientType.FILLING);
        System.out.println("✓ Равенство и неравенство элементов работает корректно");
    }

    @Test
    public void testOrdinal() {
        System.out.println("Тестирование порядковых номеров элементов перечисления");
        assertEquals(0, IngredientType.SAUCE.ordinal());
        assertEquals(1, IngredientType.FILLING.ordinal());
        System.out.println("✓ Порядковые номера элементов корректны");
    }

    @Test
    public void testEnumValues() {
        System.out.println("Тестирование всех значений перечисления");
        IngredientType[] types = IngredientType.values();
        assertEquals(2, types.length);
        assertTrue(EnumSet.allOf(IngredientType.class).contains(IngredientType.SAUCE));
        assertTrue(EnumSet.allOf(IngredientType.class).contains(IngredientType.FILLING));
        System.out.println("✓ Все значения перечисления присутствуют и корректны");
    }
}