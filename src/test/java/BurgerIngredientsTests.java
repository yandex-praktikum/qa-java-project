import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class BurgerIngredientsTests {

    Burger burger;

    @Mock
    Ingredient ingredient1 = mock(Ingredient.class);
    @Mock
    Ingredient ingredient2 = mock(Ingredient.class);

    @Test
    public void moveIngredientCheckNewPositionPositiveTest() {
        burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(burger.ingredients.get(1), ingredient1);
    }

    @Test
    public void moveIngredientCheckOldPositionPositiveTest() {
        burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(burger.ingredients.get(0), ingredient2);
    }

    @Test
    public void addIngredientPositiveTest() {
        burger = new Burger();
        burger.addIngredient(ingredient1);
        assertTrue(burger.ingredients.contains(ingredient1));
    }

    @Test
    public void removeIngredientPositiveTest() {
        burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(ingredient1));
    }
}
