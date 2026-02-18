package praktikum;

import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BurgerIngredientsTest {
    @Test
    public void addIngredientsTest () {
        Burger burger = new Burger();

        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest () {
        Burger burger = new Burger();

        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();

        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        burger.moveIngredient(0, 2);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient3, burger.ingredients.get(1));
        assertEquals(ingredient1, burger.ingredients.get(2));

    }
}
