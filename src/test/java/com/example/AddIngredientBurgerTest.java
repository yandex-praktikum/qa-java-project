package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class AddIngredientBurgerTest {
    private Burger burger;
    private final Ingredient testIngredient;

    public AddIngredientBurgerTest(Ingredient ingredient) {
        this.testIngredient = ingredient;
    }

    @Parameterized.Parameters(name = "Тестируемый ингредиент: {0}")
    public static List<Object[]> testIngredients() {
        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getName()).thenReturn("Соус");
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getName()).thenReturn("Начинка");
        when(filling.getType()).thenReturn(IngredientType.FILLING);

        return Arrays.asList(
                new Object[]{sauce},
                new Object[]{filling}
        );
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void addIngredientShouldContainAddedIngredient() {
        burger.addIngredient(testIngredient);
        assertTrue("Ингредиент должен присутствовать в бургере",
                burger.ingredients.contains(testIngredient));
    }

    @Test
    public void addIngredientShouldAddToEndOfList() {
        burger.addIngredient(testIngredient);
        int lastIndex = burger.ingredients.size() - 1;
        assertEquals("Ингредиент должен добавляться в конец списка",
                testIngredient, burger.ingredients.get(lastIndex));
    }
}