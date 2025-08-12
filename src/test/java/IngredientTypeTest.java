import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class IngredientTypeTest {

    @Test
    @Parameters({
            "SAUCE, SAUCE",
            "FILLING, FILLING"
    })
    public void testIngredientTypeValueOf(String name, IngredientType expectedType) {
        assertEquals(expectedType, IngredientType.valueOf(name));
    }
}