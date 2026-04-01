package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientSauce;

    @Mock
    private Ingredient ingredientFilling;

    // Создать бургер (выполняем для каждого теста)
    @Before
    public void setUp() {
        burger = new Burger();
        bun = mock(Bun.class);
        ingredientSauce = mock(Ingredient.class);
        ingredientFilling = mock(Ingredient.class);

        // Создать стабы для переменных теста "Получить рецепт и его чек"
        // Булка
        when(bun.getName()).thenReturn("Black Bun");
        when(bun.getPrice()).thenReturn(100f);

        // Соус
        when(ingredientSauce.getName()).thenReturn("Cheesy");
        when(ingredientSauce.getPrice()).thenReturn(50f);
        when(ingredientSauce.getType()).thenReturn(IngredientType.SAUCE);

        // Начинка
        when(ingredientFilling.getName()).thenReturn("Chicken");
        when(ingredientFilling.getPrice()).thenReturn(150f);
        when(ingredientFilling.getType()).thenReturn(IngredientType.FILLING);
    }

    // Тест "Положить булку для бургера"
    @Test
    public void setBunsTest() {
        burger.setBuns(bun); // Добавить булку
        Assert.assertEquals("Ожидаем другую булку", bun, burger.bun ); // Проверить что булка добавилась
    }

    // Тест "Добавить ингредиент в бургер"
    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredientSauce); // Добавить ингредиент
        Assert.assertEquals("Ожидаем другой ингредиент", List.of(ingredientSauce), burger.ingredients); // Проверить что ингредиент добавился
    }

    // Тест "Удалить ингредиент из бургера"
    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredientFilling); // Добавить ингредиент
        burger.removeIngredient(0); // Удалить ингредиент
        Assert.assertTrue("Ингредиент не удалили", burger.ingredients.isEmpty()); // Проверить что ингредиент удалился
    }

    // Тест "Переместить ингредиенты в бургере"
    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredientFilling); // Добавить начинку
        burger.addIngredient(ingredientSauce); // Добавить соус
        burger.moveIngredient(1,0); // Поменять местами ингредиенты
        Assert.assertEquals("Первым ингредиентом должен стать соус", ingredientSauce, burger.ingredients.get(0)); // Проверить что ингредиенты поменялись местами
    }

    // Тест "Получить рецепт и его чек"
    @Test
    public void getReceiptTest() {
        burger.setBuns(bun); // Добавить булку
        burger.addIngredient(ingredientSauce); // Добавить соус
        burger.addIngredient(ingredientFilling); // Добавить начинку

        String actualReceipt = burger.getReceipt(); // Фактический результат

        // Создать ожидаемый результат
        String expectedReceipt =
                String.format("(==== %s ====)%n", bun.getName()) +
                        String.format("= %s %s =%n", ingredientSauce.getType().toString().toLowerCase(),
                                ingredientSauce.getName()) +
                        String.format("= %s %s =%n", ingredientFilling.getType().toString().toLowerCase(),
                                ingredientFilling.getName()) +
                        String.format("(==== %s ====)%n", bun.getName()) +
                        String.format("%nPrice: %f%n", burger.getPrice());
        System.out.println("Receipt:");
        System.out.println(burger.getReceipt());

        // Проверить что результаты совпадают
        Assert.assertEquals("Pезультаты не совпадают",expectedReceipt, actualReceipt);
    }

}
