import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientParameterizedTest {
    private final int index;
    private final int newIndex;

    public BurgerMoveIngredientParameterizedTest(int index, int newIndex) {
        this.index = index;
        this.newIndex = newIndex;
    }

    @Parameterized.Parameters(name = "move: {0}, to: {1}")
    public static Object[][] getMoveData() {
        return new Object[][]{
                {0, 1},
                {0, 2},
                {1, 0},
                {1, 1},
                {1, 2},
                {2, 0},
                {2, 1}

        };
    }

    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();
        Ingredient firstIngredient = new Ingredient(IngredientType.FILLING, "chicken fillet", 2.3f);
        Ingredient secondIngredient = new Ingredient(IngredientType.SAUCE, "mayo", 2.3f);
        Ingredient thirdIngredient = new Ingredient(IngredientType.FILLING, "lettuce", 2.3f);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        Ingredient expectedIndex = burger.ingredients.get(index);

        burger.moveIngredient(index, newIndex);

        assertEquals(expectedIndex, burger.ingredients.get(newIndex));

    }
}
