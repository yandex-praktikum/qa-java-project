package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {

    private final IngredientType type;
    private final String name;

    public IngredientParameterizedTest(IngredientType type, String name) {
        this.type = type;
        this.name = name;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {IngredientType.SAUCE, "Соус"},
                {IngredientType.FILLING, "Начинка"}
        };
    }

    @Test
    public void ingredientShouldHaveCorrectTypeAndName() {
        Ingredient ingredient = new Ingredient(type, name, 100);
        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
    }
}