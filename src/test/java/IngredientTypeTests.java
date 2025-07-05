import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTests {
    private final String typeName;

    @Parameterized.Parameters(name = "Test {index}: {0}")
    public static Object[][] data() {
        return new Object[][]{
                {"SAUCE"},
                {"FILLING"}
        };
    }

    public IngredientTypeTests(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Проверка правильности типов ингредиентов.
     */
    @Test
    public void ingredientTypeTest() {
        assertEquals("Тип ингредиента не соответствует ожидаемому", typeName,
                IngredientType.valueOf(typeName).toString());
    }
}
