package ru.practicum.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import praktikum.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetReceiptWithoutIngredients() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("Test Bun");
        when(bun.getPrice()).thenReturn(7.0f);
        burger.setBuns(bun);

        String expected = String.format("(==== %s ====)%n", "Test Bun");
        expected += String.format("(==== %s ====)%n", "Test Bun");
        expected += String.format("%nPrice: %f%n", burger.getPrice());

        String actual = burger.getReceipt();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetReceiptWithIngredientsFormatAndOrder() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("White Bun");
        when(bun.getPrice()).thenReturn(2.0f);
        burger.setBuns(bun);

        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("Ketchup");
        when(sauce.getPrice()).thenReturn(0.5f);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("Beef Patty");
        when(filling.getPrice()).thenReturn(3.0f);

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        StringBuilder expected = new StringBuilder(String.format("(==== %s ====)%n", "White Bun"));
        expected.append(String.format("= %s %s =%n", "sauce", "Ketchup"));
        expected.append(String.format("= %s %s =%n", "filling", "Beef Patty"));
        expected.append(String.format("(==== %s ====)%n", "White Bun"));
        expected.append(String.format("%nPrice: %f%n", burger.getPrice()));

        assertEquals(expected.toString(), burger.getReceipt());
    }

    // --- Добавление: по одному утверждению на тест ---
    @Test
    public void testAddIngredientsSize() {
        Burger burger = new Burger();
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void testFirstIngredientIsAAfterAdd() {
        Burger burger = new Burger();
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        assertSame(a, burger.ingredients.get(0));
    }

    @Test
    public void testSecondIngredientIsBAfterAdd() {
        Burger burger = new Burger();
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        assertSame(b, burger.ingredients.get(1));
    }

    // --- Удаление: по одному утверждению на тест ---
    @Test
    public void testRemoveIngredientReducesSize() {
        Burger burger = new Burger();
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        burger.removeIngredient(1);

        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientShiftsElements() {
        Burger burger = new Burger();
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        burger.removeIngredient(1);

        assertSame(c, burger.ingredients.get(1));
    }

    // --- Перемещение: одна проверка ---
    @Test
    public void testMoveIngredientReorders() {
        Burger burger = new Burger();
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        // повторяем сценарий: удалить средний, затем переместить последний на позицию 0
        burger.removeIngredient(1);
        burger.moveIngredient(1, 0);

        assertSame(c, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientThrowsWhenIndexInvalid() {
        Burger burger = new Burger();
        burger.removeIngredient(0);
    }
}