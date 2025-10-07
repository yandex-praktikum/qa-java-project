package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class IngredientTypeTest {
    private final String typeName;

    @Parameterized.Parameters(name = "Наличие типа {0}")
    public static Object[][] paramsForTest() {
        return new Object[][]{
                {"SAUCE"},
                {"FILLING"}
        };
    }

    public IngredientTypeTest(String typeName) {
        this.typeName = typeName;
    }

    @Test
    public void ingredientTypeIsCorrectList() {

        MatcherAssert.assertThat("Отсутствует элемент " + typeName,
                IngredientType.valueOf(typeName.toUpperCase()).toString(),
                equalTo(typeName));
    }
}