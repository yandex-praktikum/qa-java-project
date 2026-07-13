package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IngredientTypeTest {
    @Test
    public void ingredientTypeEnumContainsRequiredValues() {
        Assertions.assertEquals(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
        Assertions.assertEquals(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }
}
