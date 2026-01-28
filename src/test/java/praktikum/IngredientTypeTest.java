package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private final IngredientType type;
    private final String expectedName;

    public IngredientTypeTest(IngredientType type, String expectedName) {
        this.type = type;
        this.expectedName = expectedName;
    }

    @Parameterized.Parameters(name = "{index}: type={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"}
        });
    }

    @Test
    public void valueOfShouldReturnCorrectEnumConstant() {
        IngredientType actual = IngredientType.valueOf(expectedName);
        assertEquals(type, actual);
    }

    @Test
    public void toStringShouldReturnEnumName() {
        assertEquals(expectedName, type.toString());
    }
}
