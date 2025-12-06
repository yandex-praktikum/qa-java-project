package ru.yandex.practicum.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBun_shouldSetValidBunTest() {

        burger.setBuns(bun);
        assertEquals("Булочка должна быть установлена корректно", bun, burger.bun);
    }

    @Test // увеличиваем на один
    public void addIngredient_ShouldIncreaseByOneTest() {

        burger.addIngredient(sauce);
        assertEquals("Размер списка ингредиентов должен увеличиться на 1", 1, burger.ingredients.size());
    }

    @Test // проверяем, что элемент попал в конец
    public void addIngredient_ShouldAddToEndTest() {

        burger.addIngredient(sauce);
        assertEquals("Добавленный ингредиент должен быть последним элементом списка", sauce, burger.ingredients.get(burger.ingredients.size() - 1));
    }
}
