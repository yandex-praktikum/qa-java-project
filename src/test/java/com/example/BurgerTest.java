package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals("Ошибка при задании булочки", mockBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals("Ошибка при добавлении ингредиента", mockIngredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        assertTrue(burger.ingredients.contains(mockIngredient1));
        burger.removeIngredient(burger.ingredients.indexOf(mockIngredient1));
        assertFalse("Ошибка при удалении ингредиента", burger.ingredients.contains(mockIngredient1));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.moveIngredient(0, 1);
        assertEquals("Ингредиенты не правильно перемещены", mockIngredient2, burger.ingredients.get(0));
    }

    @Test
    public void testGetPrice() {
        Mockito.when(mockBun.getPrice()).thenReturn(500.0f);
        Mockito.when(mockIngredient1.getPrice()).thenReturn(1000.0f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        assertEquals("Цена бургера рассчитана неправильно", 2000.0f, burger.getPrice(), 0.0f);
    }

    @Test
    public void testGetReceipt() {
        Mockito.when(mockBun.getName()).thenReturn("black bun");
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        Mockito.when(mockIngredient1.getName()).thenReturn("hot sauce");
        Mockito.when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredient1.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        assertEquals("Квитанция о бургере не соответствует ожидаемой", makeReceipt(burger), burger.getReceipt());
    }

    private String makeReceipt(Burger burger) {
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", burger.bun.getName()));

        for (Ingredient ingredient : burger.ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n", burger.bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));

        return receipt.toString();
    }
}
