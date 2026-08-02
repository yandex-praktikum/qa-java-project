package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    @Test
    public void setBunsShouldSetBun() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);

        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldIncreaseIngredientsCount() {
        Burger burger = new Burger();
        Ingredient cutlet = mock(Ingredient.class);

        burger.addIngredient(cutlet);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldDecreaseIngredientsCount() {
        Burger burger = new Burger();

        Ingredient cutlet = mock(Ingredient.class);
        Ingredient hotSauce = mock(Ingredient.class);

        burger.addIngredient(cutlet);
        burger.addIngredient(hotSauce);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldKeepRemainingIngredient() {
        Burger burger = new Burger();

        Ingredient cutlet = mock(Ingredient.class);
        Ingredient hotSauce = mock(Ingredient.class);

        burger.addIngredient(cutlet);
        burger.addIngredient(hotSauce);

        burger.removeIngredient(0);

        assertSame(hotSauce, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToFirstPosition() {
        Burger burger = new Burger();

        Ingredient cutlet = mock(Ingredient.class);
        Ingredient hotSauce = mock(Ingredient.class);
        Ingredient sausage = mock(Ingredient.class);

        burger.addIngredient(cutlet);
        burger.addIngredient(hotSauce);
        burger.addIngredient(sausage);

        burger.moveIngredient(0, 2);

        assertSame(hotSauce, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToSecondPosition() {
        Burger burger = new Burger();

        Ingredient cutlet = mock(Ingredient.class);
        Ingredient hotSauce = mock(Ingredient.class);
        Ingredient sausage = mock(Ingredient.class);

        burger.addIngredient(cutlet);
        burger.addIngredient(hotSauce);
        burger.addIngredient(sausage);

        burger.moveIngredient(0, 2);

        assertSame(sausage, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToLastPosition() {
        Burger burger = new Burger();

        Ingredient cutlet = mock(Ingredient.class);
        Ingredient hotSauce = mock(Ingredient.class);
        Ingredient sausage = mock(Ingredient.class);

        burger.addIngredient(cutlet);
        burger.addIngredient(hotSauce);
        burger.addIngredient(sausage);

        burger.moveIngredient(0, 2);

        assertSame(cutlet, burger.ingredients.get(2));
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(300f, burger.getPrice(), 0.001f);
    }
}