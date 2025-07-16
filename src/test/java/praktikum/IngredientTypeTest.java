package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private IngredientType type;

    public IngredientTypeTest(IngredientType type) {
        this.type = type;
    }

    @Parameterized.Parameters(name = "Test enum value: {0}")
    public static Collection<IngredientType> data() {
        return Arrays.asList(IngredientType.values());
    }

    @Test
    public void testIngredientTypeValues() {
        assertNotNull(type);
        assertTrue(type == IngredientType.SAUCE || type == IngredientType.FILLING);
    }
}
