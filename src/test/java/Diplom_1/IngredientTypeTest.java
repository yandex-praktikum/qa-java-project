package Diplom_1;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private IngredientType ingredientType;
    private IngredientType expectedIngredientType;

    public IngredientTypeTest(IngredientType ingredientType, IngredientType expectedIngredientType) {
        this.ingredientType = ingredientType;
        this.expectedIngredientType = expectedIngredientType;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][] {
                {SAUCE, SAUCE},
                {FILLING, FILLING}
        };
    }

    @Test
    public void getTypeIngredientWithTestData() {
        Ingredient ingredient = new Ingredient(ingredientType, "delicious", 100);
        assertEquals(expectedIngredientType, ingredient.getType());
    }
}