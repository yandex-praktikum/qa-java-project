package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTest {
    private final IngredientType type;
    private final String expectedName;

    public IngredientTypeTest(IngredientType type, String expectedName) {
        this.type = type;
        this.expectedName = expectedName;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"}
        };
    }

    @Test
    public void ingredientTypeHasCorrectValue() {
        assertEquals(expectedName, type.name());
    }
}