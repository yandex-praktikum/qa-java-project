package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    @Parameterized.Parameters(name = "Type = {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE},
                {IngredientType.FILLING}
        });
    }

    @Parameterized.Parameter
    public IngredientType type;

    @Test
    public void ingredientTypeIsNotNull() {
        assertNotNull(type);
    }
}