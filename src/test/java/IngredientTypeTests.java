import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTests {

    private IngredientType ingredientType;
    private String expectedName;

    // Конструктор для передачи параметров
    public IngredientTypeTests(IngredientType ingredientType, String expectedName) {
        this.ingredientType = ingredientType;
        this.expectedName = expectedName;
    }

    // Таблица данных для параметризации (все элементы перечисления)
    @Parameterized.Parameters(name="{index}: Testing ingredient type {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"}
        });
    }

    // Тест 1: Проверка соответствия названия элемента перечисления
    @Test
    public void testEnumValue() {
        assertEquals(expectedName, ingredientType.name());
    }

    // Тест 2: Проверка получения элемента перечисления по индексу
    @Test
    public void testValuesAccess() {
        IngredientType[] values = IngredientType.values();
        assertEquals(values[ingredientType.ordinal()], ingredientType);
    }

    // Тест 3: Проверка доступности всех элементов перечисления
    @Test
    public void testValuesSize() {
        IngredientType[] values = IngredientType.values();
        assertEquals(2, values.length); // Проверяем, что всего два элемента
    }
}