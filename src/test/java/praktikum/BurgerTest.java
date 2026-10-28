package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Before
    public void setUp() {
        // Инициализация объекта Burger перед каждым тестом
        burger = new Burger();
    }

    // Тест для метода setBuns класса Burger
    @Test
    public void testSetBuns() {
        // Создание мока булочки
        Bun bun = mock(Bun.class);

        // Выбор булочки
        burger.setBuns(bun);

        // Проверяем выбор булочки
        Assert.assertSame(bun, burger.bun);
    }

    // Тест для метода addIngredient класса Burger
    @Test
    public void testAddIngredient() {
        // Создаем мок (заглушку) для объекта Ingredient с помощью Mockito
        Ingredient ingredient = mock(Ingredient.class);

        // Добавляем ингредиент в burger с помощью метода addIngredient
        burger.addIngredient(ingredient);

        // Проверяем, что размер списка ingredients увеличился на 1 с помощью assertEquals
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsIngredientToList() {
        // Создаем мок (заглушку) для объекта Ingredient с помощью Mockito
        Ingredient ingredientToAdd = mock(Ingredient.class);

        // Добавляем ингредиент в burger с помощью метода addIngredient
        burger.addIngredient(ingredientToAdd);

        // Проверяем, что добавленный ингредиент содержится в списке ingredients с помощью assertTrue
        Assert.assertTrue(burger.ingredients.contains(ingredientToAdd));
    }


    // Тест для метода removeIngredient класса Burger
    @Test
    public void testRemoveIngredientDecreasesSize() {
        // Создаем две заглушки (моки) для объектов Ingredient с помощью Mockito
        Ingredient ingredientToRemove = mock(Ingredient.class);
        Ingredient remainingIngredient = mock(Ingredient.class);

        // Добавляем оба ингредиента в burger
        burger.addIngredient(ingredientToRemove);
        burger.addIngredient(remainingIngredient);

        // Удаляем первый ингредиент с индексом 0
        burger.removeIngredient(0);

        // Проверяем, что размер списка ingredients уменьшился на 1 с помощью assertEquals
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesFirstIngredient() {
        // Создаем две заглушки (моки) для объектов Ingredient с помощью Mockito
        Ingredient ingredientToRemove = mock(Ingredient.class);
        Ingredient remainingIngredient = mock(Ingredient.class);

        // Добавляем оба ингредиента в burger
        burger.addIngredient(ingredientToRemove);
        burger.addIngredient(remainingIngredient);

        // Удаляем первый ингредиент с индексом 0
        burger.removeIngredient(0);

        // Проверяем, что удаляемый ингредиент больше не содержится в списке ingredients с помощью assertFalse
        Assert.assertFalse(burger.ingredients.contains(ingredientToRemove));
    }

    @Test
    public void testRemoveIngredientKeepsSecondIngredient() {
        // Создаем две заглушки (моки) для объектов Ingredient с помощью Mockito
        Ingredient ingredientToRemove = mock(Ingredient.class);
        Ingredient remainingIngredient = mock(Ingredient.class);

        // Добавляем оба ингредиента в burger
        burger.addIngredient(ingredientToRemove);
        burger.addIngredient(remainingIngredient);

        // Удаляем первый ингредиент с индексом 0
        burger.removeIngredient(0);

        // Проверяем, что оставшийся ингредиент по-прежнему содержится в списке ingredients с помощью assertTrue
        Assert.assertTrue(burger.ingredients.contains(remainingIngredient));
    }

    // Тест для метода moveIngredient класса Burger
    @Test
    public void testMoveIngredientKeepsSize() {
        // Создаем три заглушки (моки) для объектов Ingredient с помощью Mockito
        Ingredient ingredientToMove = mock(Ingredient.class);
        Ingredient ingredientAtStart = mock(Ingredient.class);
        Ingredient ingredientInMiddle = mock(Ingredient.class);

        // Добавляем все три ингредиента в burger
        burger.addIngredient(ingredientToMove);
        burger.addIngredient(ingredientAtStart);
        burger.addIngredient(ingredientInMiddle);

        // Перемещаем первый ингредиент с индексом 0 на позицию с индексом 2
        burger.moveIngredient(0, 2);

        // Проверяем, что размер списка ingredients остался неизменным (3) с помощью assertEquals
        Assert.assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientMovesFirstIngredientToIndexTwo() {
        // Создаем три заглушки (моки) для объектов Ingredient с помощью Mockito
        Ingredient ingredientToMove = mock(Ingredient.class);
        Ingredient ingredientAtStart = mock(Ingredient.class);
        Ingredient ingredientInMiddle = mock(Ingredient.class);

        // Добавляем все три ингредиента в burger
        burger.addIngredient(ingredientToMove);
        burger.addIngredient(ingredientAtStart);
        burger.addIngredient(ingredientInMiddle);

        // Перемещаем первый ингредиент с индексом 0 на позицию с индексом 2
        burger.moveIngredient(0, 2);

        // Проверяем, что перемещённый ингредиент находится на индексе 2
        Assert.assertSame(ingredientToMove, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientMovesSecondIngredientToIndexZero() {
        // Создаем три заглушки (моки) для объектов Ingredient с помощью Mockito
        Ingredient ingredientToMove = mock(Ingredient.class);
        Ingredient ingredientAtStart = mock(Ingredient.class);
        Ingredient ingredientInMiddle = mock(Ingredient.class);

        // Добавляем все три ингредиента в burger
        burger.addIngredient(ingredientToMove);
        burger.addIngredient(ingredientAtStart);
        burger.addIngredient(ingredientInMiddle);

        // Перемещаем первый ингредиент с индексом 0 на позицию с индексом 2
        burger.moveIngredient(0, 2);

        // Проверяем, что ингредиент, изначально находившийся на индексе 1, переместился на индекс 0
        Assert.assertSame(ingredientAtStart, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientMovesThirdIngredientToIndexOne() {
        // Создаем три заглушки (моки) для объектов Ingredient с помощью Mockito
        Ingredient ingredientToMove = mock(Ingredient.class);
        Ingredient ingredientAtStart = mock(Ingredient.class);
        Ingredient ingredientInMiddle = mock(Ingredient.class);

        // Добавляем все три ингредиента в burger
        burger.addIngredient(ingredientToMove);
        burger.addIngredient(ingredientAtStart);
        burger.addIngredient(ingredientInMiddle);

        // Перемещаем первый ингредиент с индексом 0 на позицию с индексом 2
        burger.moveIngredient(0, 2);

        // Проверяем, что ингредиент, изначально находившийся на индексе 2, переместился на индекс 1
        Assert.assertSame(ingredientInMiddle, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(200.0f);
        when(ingredient.getPrice()).thenReturn(150.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Assert.assertEquals(550.0f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt() {
        Bun bun = mock(Bun.class);
        Ingredient sauceToAdd = mock(Ingredient.class);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100.0f);
        when(sauceToAdd.getType()).thenReturn(praktikum.IngredientType.SAUCE);
        when(sauceToAdd.getName()).thenReturn("hot sauce");
        when(sauceToAdd.getPrice()).thenReturn(100.0f);

        burger.setBuns(bun);
        burger.addIngredient(sauceToAdd);

        String expected = String.format(
                "(==== black bun ====)%n" +
                        "= sauce hot sauce =%n" +
                        "(==== black bun ====)%n" +
                        "%nPrice: %f%n",
                300.0f
        );

        Assert.assertEquals(expected, burger.getReceipt());
    }
}