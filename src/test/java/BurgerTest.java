import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.mockito.Mockito.*;

import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    // Создаём новый объект Burger перед каждым тестом
    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        // Создаём мок булочки
        Bun bun = mock(Bun.class);

        // Устанавливаем булочку для бургера
        burger.setBuns(bun);

        // Проверяем, что в бургере была именно эта булочка
        Assert.assertSame(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        // Создаём мок ингредиента
        Ingredient ingredient = mock(Ingredient.class);

        // Добавляем ингредиент в бургер
        burger.addIngredient(ingredient);

        // Проверяем, что в списке появился один ингредиент
        Assert.assertEquals(1, burger.ingredients.size());

        // Проверяем, что добавленный ингредиент находится в списке
        Assert.assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void testRemoveIngredient() {
        // Создаём два мока ингредиентов
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        // Добавляем два ингредиента в бургер
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        // Удаляем первый ингредиент по индексу
        burger.removeIngredient(0);

        // Проверяем, что в списке остался один ингредиент
        Assert.assertEquals(1, burger.ingredients.size());

        // Проверяем, что первый ингредиент был удалён
        Assert.assertFalse(burger.ingredients.contains(ingredient1));

        // Проверяем, что второй ингредиент остался в списке
        Assert.assertTrue(burger.ingredients.contains(ingredient2));
    }

    @Test
    public void testMoveIngredient() {
        // Создаём три мока ингредиентов
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        // Добавляем ингредиенты в бургер в исходном порядке:
        // ingredient1 -> ingredient2 -> ingredient3
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        // Перемещаем первый ингредиент с индекса 0 на индекс 2
        burger.moveIngredient(0, 2);

        // Проверяем, что количество ингредиентов не изменилось
        Assert.assertEquals(3, burger.ingredients.size());

        // Проверяем новый порядок ингредиентов:
        // ingredient2 -> ingredient3 -> ingredient1
        Assert.assertSame(ingredient1, burger.ingredients.get(2));
        Assert.assertSame(ingredient2, burger.ingredients.get(0));
        Assert.assertSame(ingredient3, burger.ingredients.get(1));
    }
}