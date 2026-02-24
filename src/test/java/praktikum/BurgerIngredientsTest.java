package praktikum;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BurgerIngredientsTest {

    @Test
    public void addIngredientsTest () {
        Burger burger = new Burger();

        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        assertEquals(Arrays.asList(ingredient), burger.ingredients);
    }

    @Test
    public void removeIngredientTest () {
        Burger burger = new Burger();

        Ingredient one = mock(Ingredient.class);
        Ingredient two = mock(Ingredient.class);
        burger.addIngredient(one);
        burger.addIngredient(two);

        burger.removeIngredient(0);
        assertEquals(Arrays.asList(two), burger.ingredients);
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();

        Ingredient one = mock(Ingredient.class);
        Ingredient two = mock(Ingredient.class);
        Ingredient three = mock(Ingredient.class);

        burger.addIngredient(one);
        burger.addIngredient(two);
        burger.addIngredient(three);

        burger.moveIngredient(0, 2);
        List<Ingredient> expected = Arrays.asList(two, three, one);
        assertEquals(expected, burger.ingredients);

    }
}
