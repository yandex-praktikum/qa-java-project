import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeParametrizedTest {
    private final IngredientType type;
    private final String name;

    public IngredientTypeParametrizedTest(IngredientType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Parameterized.Parameters(name = "{index} : name = {0}")
    public static Object[][] getIngredientTypeData() {
        return new Object[][] {
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"}
        };
    }

    @Test
    public void checkIngredientTypeName() {
        assertEquals("Incorrect name ingredient type", name, type.name());
    }
}