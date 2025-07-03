package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @InjectMocks
    private Burger burger;

    @Before
    public void setUp() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(ingredient1.getName()).thenReturn("cutlet");
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient1.getPrice()).thenReturn(50f);

        when(ingredient2.getName()).thenReturn("chili sauce");
        when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient2.getPrice()).thenReturn(30f);
    }

    @Test
    public void setBuns_ShouldSetBunCorrectly() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredient_ShouldAddIngredientToList() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredient_ShouldAddCorrectIngredient() {
        burger.addIngredient(ingredient1);
        assertSame(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredient_ShouldRemoveIngredientFromList() {
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredient_ShouldChangeIngredientsOrder() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        assertSame(ingredient2, burger.ingredients.get(0));
        assertSame(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPrice_ShouldReturnCorrectPriceForOnlyBun() {
        burger.setBuns(bun);
        assertEquals(200f, burger.getPrice(), 0.0f);
    }

    @Test
    public void getPrice_ShouldReturnCorrectPriceForBunAndOneIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        assertEquals(250f, burger.getPrice(), 0.0f);
    }

    @Test
    public void getPrice_ShouldReturnCorrectPriceForBunAndMultipleIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(280f, burger.getPrice(), 0.0f);
    }

    @Test
    public void getReceipt_ShouldContainCorrectBunName() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== black bun ====)"));
    }

    @Test
    public void getReceipt_ShouldContainCorrectIngredientInfo() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("= filling cutlet ="));
    }

    @Test
    public void getReceipt_ShouldContainCorrectTotalPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 250.0"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredient_ShouldThrowExceptionWhenIndexInvalid() {
        burger.removeIngredient(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredient_ShouldThrowExceptionWhenIndexInvalid() {
        burger.moveIngredient(0, 1);
    }
}