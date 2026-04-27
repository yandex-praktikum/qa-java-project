package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final String ingredientTypeName;
    private final IngredientType expectedIngredientType;

    public IngredientTypeTest(String ingredientTypeName, IngredientType expectedIngredientType) {
        this.ingredientTypeName = ingredientTypeName;
        this.expectedIngredientType = expectedIngredientType;
    }

    @Parameterized.Parameters(name = "ingredient type = {0}")
    public static Collection<Object[]> getIngredientTypeData() {
        return Arrays.asList(new Object[][]{
                {"SAUCE", IngredientType.SAUCE},
                {"FILLING", IngredientType.FILLING}
        });
    }

    @Test
    public void valueOfReturnsIngredientTypeByName() {
        assertEquals(expectedIngredientType, IngredientType.valueOf(ingredientTypeName));
    }

    @Test
    public void toStringReturnsIngredientTypeName() {
        assertEquals(ingredientTypeName, expectedIngredientType.toString());
    }
}