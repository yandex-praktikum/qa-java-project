package praktikum;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {

    @Test
    public void checkSauceEnumValueExists() {
        // Проверяем, что значение SAUCE существует в перечислении
        assertNotNull("The SAUCE enum value should exist.", IngredientType.valueOf("SAUCE"));
    }

    @Test
    public void checkFillingEnumValueExists() {
        // Проверяем, что значение FILLING существует в перечислении
        assertNotNull("The FILLING enum value should exist.", IngredientType.valueOf("FILLING"));
    }
}
