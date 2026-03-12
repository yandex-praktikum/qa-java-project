package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static praktikum.IngredientType.*;

public class IngredientTypeTest {

    public static Object[][] data() {
        return new Object[][]{
                {SAUCE},
                {FILLING}
        };
    }

    @Test
    public void countEnumValues() {
        IngredientType[] types = IngredientType.values();
        assertEquals(2, types.length, "Ожидается 2 типа ингредиента");
    }

    @ParameterizedTest
    @MethodSource("data")
    public void enumContainsExpectedValues(IngredientType name) {
        IngredientType[] types = IngredientType.values();
        assertTrue(Arrays.asList(types).contains(name));
    }
}
