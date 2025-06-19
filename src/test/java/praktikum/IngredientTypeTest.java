package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final String typeName;
    private final IngredientType expectedType;

    public IngredientTypeTest(String typeName, IngredientType expectedType) {
        this.typeName = typeName;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"SAUCE", IngredientType.SAUCE},
                {"FILLING", IngredientType.FILLING}
        };
    }

    @Test
    public void testValueOf() {
        assertEquals(expectedType, IngredientType.valueOf(typeName));
    }
}