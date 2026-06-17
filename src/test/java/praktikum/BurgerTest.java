package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    // --- Тесты для setBuns ---
    @Test
    public void setBunsShouldSetBun() {
        Burger burger = new Burger();
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    // --- Тесты для addIngredient ---
    @Test
    public void addIngredientShouldAddIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient1, burger.ingredients.get(0));
    }

    // --- Тесты для getPrice ---
    @Test
    public void getPriceShouldCalculateCorrectlyWithIngredients() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        when(mockIngredient2.getPrice()).thenReturn(75.0f);

        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float actualPrice = burger.getPrice();

        float expectedPrice = 100.0f * 2 + 50.0f + 75.0f;
        assertEquals(expectedPrice, actualPrice, 0.001f);

        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    public void getPriceShouldCalculateCorrectlyWithoutIngredients() {
        when(mockBun.getPrice()).thenReturn(150.0f);

        Burger burger = new Burger();
        burger.setBuns(mockBun);

        assertEquals(300.0f, burger.getPrice(), 0.001f);
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void getPriceShouldCalculateCorrectlyWithRealObjects() {
        // Создаем реальные объекты, а не моки
        Bun realBun = new Bun("black bun", 100.0f);
        Ingredient realIngredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 50.0f);
        Ingredient realIngredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 75.0f);

        Burger burger = new Burger();
        burger.setBuns(realBun);
        burger.addIngredient(realIngredient1);
        burger.addIngredient(realIngredient2);

        float expectedPrice = 100.0f * 2 + 50.0f + 75.0f;
        float actualPrice = burger.getPrice();

        assertEquals("Цена должна быть 325.0", expectedPrice, actualPrice, 0.001f);
    }

    // --- Тесты для getReceipt ---
    @Test
    public void getReceiptShouldGenerateCorrectReceiptWithIngredients() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("hot sauce");
        when(mockIngredient1.getPrice()).thenReturn(100.0f);
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("cutlet");
        when(mockIngredient2.getPrice()).thenReturn(100.0f);

        Burger burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String actualReceipt = burger.getReceipt();

        assertTrue("Должна быть верхняя булочка",
                actualReceipt.contains("(==== black bun ====)"));
        assertTrue("Должен быть соус",
                actualReceipt.contains("= sauce hot sauce ="));
        assertTrue("Должна быть начинка",
                actualReceipt.contains("= filling cutlet ="));
        assertTrue("Должна быть цена",
                actualReceipt.contains("Price:"));
        assertTrue("Цена должна быть 400",
                actualReceipt.contains("400"));

        verify(mockBun, times(2)).getName();
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getType();
        verify(mockIngredient1, times(1)).getName();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getType();
        verify(mockIngredient2, times(1)).getName();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    public void getReceiptShouldGenerateCorrectReceiptWithoutIngredients() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(200.0f);

        Burger burger = new Burger();
        burger.setBuns(mockBun);

        String actualReceipt = burger.getReceipt();

        assertTrue("Должна быть верхняя булочка",
                actualReceipt.contains("(==== white bun ====)"));
        assertTrue("Должна быть нижняя булочка",
                actualReceipt.contains("(==== white bun ====)"));
        assertTrue("Должна быть цена",
                actualReceipt.contains("Price:"));
        assertTrue("Цена должна быть 400",
                actualReceipt.contains("400"));

        verify(mockBun, times(2)).getName();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void getReceiptShouldGenerateCorrectReceiptWithRealObjects() {
        // Создаем реальные объекты
        Bun realBun = new Bun("black bun", 100.0f);
        Ingredient realIngredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 50.0f);
        Ingredient realIngredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 75.0f);

        Burger burger = new Burger();
        burger.setBuns(realBun);
        burger.addIngredient(realIngredient1);
        burger.addIngredient(realIngredient2);

        String receipt = burger.getReceipt();

        // Проверяем содержимое чека
        assertTrue("Должна быть верхняя булочка", receipt.contains("(==== black bun ====)"));
        assertTrue("Должен быть соус", receipt.contains("= sauce hot sauce ="));
        assertTrue("Должна быть начинка", receipt.contains("= filling cutlet ="));
        assertTrue("Должна быть нижняя булочка", receipt.contains("(==== black bun ====)"));
        assertTrue("Должна быть цена", receipt.contains("Price:"));
        assertTrue("Цена должна быть 325", receipt.contains("325"));
    }

}
