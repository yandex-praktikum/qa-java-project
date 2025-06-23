package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class AddIngredientBurgerTest {
    private static final Database database = new Database();
    private Burger burger;
    private final Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    public AddIngredientBurgerTest(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Parameterized.Parameters(name = "Тестовые данные:{0}")
    public static Object[][] data() {
        return database.availableIngredients()
                .stream()
                .map(ingredient -> new Object[]{ingredient})
                .toArray(Object[][]::new);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient);
        assertEquals("Ошибка при добавлении ингредиента", ingredient, burger.ingredients.get(0));
    }
}