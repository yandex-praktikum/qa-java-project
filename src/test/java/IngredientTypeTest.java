import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final IngredientType ingredientType;

    public IngredientTypeTest(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    @Parameterized.Parameters(name = "Тип ингредиента: {0}")
    public static Object[] getIngredientTypes() {
        return IngredientType.values();
    }


    @Test
    public void values_WhenEnumAccessed_ReturnsAllAvailableTypes() {
        IngredientType[] types = IngredientType.values();
        assertEquals(2, types.length);
    }

    @Test
    public void valueOf_WhenValidStringProvided_ReturnsCorrectEnum() {
        assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    public void toString_WhenCalled_ReturnsNonEmptyString() {
        assertNotNull(ingredientType.toString());
        assertFalse(ingredientType.toString().isEmpty());
    }
}