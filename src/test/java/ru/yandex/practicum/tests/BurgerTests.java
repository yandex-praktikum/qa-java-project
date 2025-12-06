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

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

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

    // проверяем, что элемент увеличивается на один
    @Test
    public void addIngredient_ShouldIncreaseByOneTest() {

        burger.addIngredient(sauce);

        assertEquals("Размер списка ингредиентов должен увеличиться на 1", 1, burger.ingredients.size());

    }

    // проверяем, что элемент попал в конец
    @Test
    public void addIngredient_ShouldAddToEndTest() {

        burger.addIngredient(sauce);

        assertEquals("Добавленный ингредиент должен быть последним элементом списка", sauce, burger.ingredients.get(burger.ingredients.size() - 1));

    }

    // проверяем, что размер списка уменьшается на 1
    @Test
    public void removeIngredient_ShouldDecreaseByOneTest() {

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals("Размер списка ингредиентов должен уменьшиться на 1", 1, burger.ingredients.size());

    }

    // проверяем, что удалён именно тот ингредиент, который ожидалось удалить
    @Test
    public void removeIngredient_ShouldRemoveCorrectTest() {

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals("После удаления первого ингредиента в списке должен остаться второй", filling, burger.ingredients.get(0));

    }

    // проверяем, что элемент с исходного индекса перемещается на указанный новый индекс
    @Test
    public void moveIngredient_ShouldMoveToNewIndexTest() {

        burger.addIngredient(sauce);   // index 0
        burger.addIngredient(filling); // index 1

        burger.moveIngredient(0, 1);

        assertEquals("После перемещения ингредиента его позиция должна измениться", sauce, burger.ingredients.get(1));
    }

    // проверяем, что количество ингредиентов сохраняется после перемещения
    @Test
    public void moveIngredient_ShouldPreserveCountTest() {

        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        int initialSize = burger.ingredients.size();

        burger.moveIngredient(0, 1);

        assertEquals("Количество ингредиентов не должно измениться после перемещения", initialSize, burger.ingredients.size());
    }

}
