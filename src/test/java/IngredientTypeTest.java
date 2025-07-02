import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

public class IngredientTypeTest {

    @Test
    void testIngredientTypeToString() {
        Assertions.assertEquals("SAUCE", IngredientType.SAUCE.name());
        Assertions.assertEquals("FILLING", IngredientType.FILLING.name());
    }
}
