package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        "SAUCE",
                        IngredientType.SAUCE.name()

                },
                {
                        "FILLING",
                        IngredientType.FILLING.name()
                }
        });
    }

    private final String expectedResult;
    private final String ingredientType;

    public IngredientTypeTest(
            String expectedResult,
            String ingredientType
    ) {
        this.expectedResult = expectedResult;
        this.ingredientType = ingredientType;
    }

    @Test
    public void ingredientTypeName() {
        assertEquals(expectedResult, ingredientType);
    }

}