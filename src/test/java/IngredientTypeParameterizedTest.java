import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeParameterizedTest {
    private IngredientType type;
    private String expectedNameType;

    public IngredientTypeParameterizedTest(IngredientType type, String expectedNameType) {
        this.type = type;
        this.expectedNameType = expectedNameType;
    }

    @Parameterized.Parameters(name = "{index} : type = {0}")
    public static Object[][] data() {
        return new Object[][] {
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"}
        };
    }

    @Test
    public void ingredientTypeTest(){
        assertEquals("Ожидаемый ингредиент не соответствует актуальному", expectedNameType, type.name());
    }
}
