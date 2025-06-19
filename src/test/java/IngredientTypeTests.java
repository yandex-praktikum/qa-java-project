import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTests {
    IngredientType ingredientType;

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    public void ingredientTypeValuesTest(IngredientType expectedIngredientType) {
        ingredientType = expectedIngredientType;
        assertEquals(expectedIngredientType, ingredientType);
    }
}
