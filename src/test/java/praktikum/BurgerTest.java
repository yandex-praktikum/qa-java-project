package praktikum;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Test
    public void testSetBuns_SetsBun() {
        Bun bun = Mockito.mock(Bun.class);
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient_AddsIngredient() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient_RemovesIngredient() {
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient_MovesIngredient() {
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice_WithMockedBunAndIngredients() {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getPrice()).thenReturn(100.0f);
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        when(ingredient1.getPrice()).thenReturn(50.0f);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        when(ingredient2.getPrice()).thenReturn(75.0f);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(325.0f, burger.getPrice(), 0.001);
        verify(bun, times(1)).getPrice();
        verify(ingredient1).getPrice();
        verify(ingredient2).getPrice();
    }

    @Test
    public void testGetReceipt_WithMockedBunAndIngredients() {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn("Булка");
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("Соус");
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== Булка ====)"));
        assertTrue(receipt.contains("= sauce Соус ="));
        verify(bun, times(2)).getName();
        verify(ingredient).getType();
        verify(ingredient).getName();
    }

    @Test
    public void testGetPrice_MultipleIngredients() {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getPrice()).thenReturn(50.0f);
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (int i = 0; i < 5; i++) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(20.0f);
            burger.addIngredient(ingredient);
        }
        assertEquals(200.0f, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceipt_EmptyBurger() {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn("Булка");
        Burger burger = new Burger();
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== Булка ====)"));
        assertEquals(2, receipt.split("\\n").length - 2);
    }

    @Test
    public void testMoveIngredient_ToSamePosition_NoChange() {
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 0);
        assertEquals(ingredient1, burger.ingredients.get(0));
        assertEquals(ingredient2, burger.ingredients.get(1));
    }

    @Test
    public void testRemoveIngredient_LastIndex() {
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void testAddIngredient_MultipleTimes() {
        Burger burger = new Burger();
        for (int i = 0; i < 10; i++) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            burger.addIngredient(ingredient);
        }
        assertEquals(10, burger.ingredients.size());
    }
}