package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class IngredientTypeParameterizedTest {

    private final String ingredientType;

    public IngredientTypeParameterizedTest(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    @Parameterized.Parameters(name = "Test {index}: checkIngredientTypeValueTest() for:   {0}")
    public static Object[][] getIngredientType() {
        return new Object[][]{
                {"SAUCE"},
                {"FILLING"},
        };
    }

    @Test
    public void checkIngredientTypeValueTest() {
        Assert.assertThat(IngredientType.valueOf(ingredientType), is(notNullValue()));

    }
}