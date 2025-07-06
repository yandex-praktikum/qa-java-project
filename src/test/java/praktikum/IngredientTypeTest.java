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
    private String expectedName;

    public IngredientTypeTest(IngredientType type, String expectedName) {
        this.type = type;
        this.expectedName = expectedName;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "SAUCE"},
                {IngredientType.FILLING, "FILLING"}
        });
    }

    @Test
    public void toStringShouldReturnCorrectName() {
        assertEquals(expectedName, type.toString());
    }

    @Test
    public void valueOfShouldReturnCorrectType() {
        IngredientType actualType = IngredientType.valueOf(expectedName);
        assertEquals(type, actualType);
    }

    @Test
    public void typesShouldHaveCorrectOrdinal() {
        if (type == IngredientType.SAUCE) {
            assertEquals(0, type.ordinal());
        } else if (type == IngredientType.FILLING) {
            assertEquals(1, type.ordinal());
        }
    }

}