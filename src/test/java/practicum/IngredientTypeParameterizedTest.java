package practicum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeParameterizedTest {

    private final IngredientType ingredientType;
    private final String ingredientTypeName;

    public IngredientTypeParameterizedTest(IngredientType ingredientType, String ingredientTypeName) {
        this.ingredientType = ingredientType;
        this.ingredientTypeName = ingredientTypeName;
    }

    @Parameterized.Parameters(name = "Тестируемый тип: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { IngredientType.SAUCE, "SAUCE" },
                { IngredientType.FILLING, "FILLING" }
        });
    }

    @Test
    public void testValueOf() {
        System.out.println("Тестирование valueOf() для " + ingredientTypeName);
        assertEquals(ingredientType, IngredientType.valueOf(ingredientTypeName));
        System.out.println("✓ valueOf() работает корректно для " + ingredientTypeName);
    }

    @Test
    public void testName() {
        System.out.println("Тестирование name() для " + ingredientType);
        assertEquals(ingredientTypeName, ingredientType.name());
        System.out.println("✓ name() работает корректно для " + ingredientType);
    }

    @Test
    public void testToString() {
        System.out.println("Тестирование toString() для " + ingredientType);
        assertEquals(ingredientTypeName, ingredientType.toString());
        System.out.println("✓ toString() работает корректно для " + ingredientType);
    }
}