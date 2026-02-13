package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final IngredientType type;

    public IngredientTypeTest(IngredientType type) {
        this.type = type;
    }

    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[] { IngredientType.SAUCE, IngredientType.FILLING };
    }

    @Test
    public void testIngredientTypeExists() {
        assertNotNull(type);
    }
}