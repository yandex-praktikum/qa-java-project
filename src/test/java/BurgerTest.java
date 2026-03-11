import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        // Настройка моков для булочки
        when(mockBun.getName()).thenReturn("White Bun");
        when(mockBun.getPrice()).thenReturn(2.5f);

        // Настройка моков для ингредиентов
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("Hot Sauce");
        when(mockIngredient1.getPrice()).thenReturn(1.0f);

        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("Cheese");
        when(mockIngredient2.getPrice()).thenReturn(1.5f);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);

        // Проверяем, что булочка установлена через verify вызова метода
        burger.getReceipt();
        verify(mockBun, atLeastOnce()).getName();
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        assertEquals("Должен быть 1 ингредиент", 1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        assertEquals("Добавлен неверный ингредиент", mockIngredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.removeIngredient(0);

        assertEquals("Ингредиент должен быть удален", 0, burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientInvalidIndex() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.removeIngredient(999); // Несуществующий индекс
    }

    @Test
    public void testGetPriceCalculatesCorrectly() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Ожидаемая цена: 2 булочки * 2.5 + 1.0 + 1.5 = 7.5
        float expectedPrice = 2 * 2.5f + 1.0f + 1.5f;
        float actualPrice = burger.getPrice();

        assertEquals("Цена рассчитана неверно", expectedPrice, actualPrice, 0.01);
    }

    @Test
    public void testGetPriceWithNoIngredients() {
        burger.setBuns(mockBun);

        float price = burger.getPrice();

        // Только цена булочек: 2 * 2.5 = 5.0
        assertEquals(5.0f, price, 0.01);
    }

    @Test
    public void testGetReceiptNotNull() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        assertNotNull("Чек не должен быть null", receipt);
    }

    @Test
    public void testGetReceiptContainsBunName() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать название булочки", receipt.contains("White Bun"));
    }

    @Test
    public void testGetReceiptContainsIngredientName() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать название ингредиента", receipt.contains("Hot Sauce"));
    }

    @Test
    public void testGetReceiptContainsPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать итоговую цену", receipt.contains("Price:"));
    }

    @Test
    public void testBurgerWithNoIngredientsReceipt() {
        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();

        assertTrue("Чек для бургера без ингредиентов должен содержать название булочки",
                receipt.contains("White Bun"));
    }
}