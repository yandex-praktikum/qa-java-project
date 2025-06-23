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
    private static final Database database = new Database();
    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testRemoveIngredient() {
        Ingredient ingredient = database.availableIngredients().get(0);
        burger.addIngredient(ingredient);
        assertTrue(burger.ingredients.contains(ingredient));
        burger.removeIngredient(burger.ingredients.indexOf(ingredient));
        assertFalse("Ошибка при удалении ингредиента", burger.ingredients.contains(ingredient));
    }

    @Test
    public void testMoveIngredient() {
        Ingredient ingredient0 = database.availableIngredients().get(0);
        Ingredient ingredient1 = database.availableIngredients().get(1);
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);
        burger.moveIngredient(0, 1);
        assertEquals("Ингредиенты не правильно перемещены", ingredient1, burger.ingredients.get(0));
        assertEquals("Ингредиенты не правильно перемещены", ingredient0, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        Mockito.when(mockBun.getPrice()).thenReturn(500.0f);
        Mockito.when(mockIngredient.getPrice()).thenReturn(1000.0f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        assertEquals("Цена бургера рассчитана неправильно", 2000.0f, burger.getPrice(), 0.0f);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(database.availableBuns().get(2));
        burger.addIngredient(database.availableIngredients().get(0));
        burger.addIngredient(database.availableIngredients().get(3));
        burger.addIngredient(database.availableIngredients().get(5));

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
