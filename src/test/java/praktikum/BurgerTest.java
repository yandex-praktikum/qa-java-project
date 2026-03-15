package praktikum;

import constants.TestMessages;
import data.TestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static praktikum.IngredientType.SAUCE;

@ExtendWith(MockitoExtension.class)
class BurgerTest {
    private Burger burger;
    private TestData data;
    @Mock private Bun bun;
    @Mock private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        burger = new Burger();
        data = new TestData();
    }

    // --- Базовые операции ---
    @Test
    void setBunsWorks() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun, TestMessages.ERROR);
    }

    @Test
    void addIngredientWorks() {
        burger.addIngredient(ingredient);
        assertTrue(burger.ingredients.contains(ingredient), TestMessages.ERROR);
    }

    @Test
    void removeIngredientWorks() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty(), TestMessages.ERROR);
    }

    @Test
    void moveIngredientWorks() {
        burger.addIngredient(ingredient);
        burger.addIngredient(mock(Ingredient.class));
        burger.moveIngredient(0, 1);
        assertEquals(ingredient, burger.ingredients.get(1), TestMessages.ERROR);
    }

    // --- Тесты цены ---
    @Test
    void getPriceCalculatesCorrectly() {
        float bunPrice = data.getPrice();
        float ingPrice = data.getPrice();

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient.getPrice()).thenReturn(ingPrice);

        assertEquals(bunPrice * 2 + ingPrice, burger.getPrice(), TestMessages.ERROR);
    }

    @Test
    void getPriceWorksWithNoIngredients() {
        float bunPrice = data.getPrice();
        burger.setBuns(bun);
        when(bun.getPrice()).thenReturn(bunPrice);
        assertEquals(bunPrice * 2, burger.getPrice(), TestMessages.ERROR);
    }

    @Test
    void getPriceCallsMethods() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        when(bun.getPrice()).thenReturn(1f);
        when(ingredient.getPrice()).thenReturn(1f);

        burger.getPrice();

        verify(bun, times(1)).getPrice();
        verify(ingredient, times(1)).getPrice();
    }

    // --- Тесты чека ---
    @Test
    void getReceiptFormatsCorrectly() {
        String bunName = data.getName();
        String ingName = data.getName();

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(1f);
        when(ingredient.getType()).thenReturn(SAUCE);
        when(ingredient.getName()).thenReturn(ingName);
        when(ingredient.getPrice()).thenReturn(1f);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(bunName));
        assertTrue(receipt.contains("sauce"));
        assertTrue(receipt.contains(ingName));
        assertTrue(receipt.contains("Price:"));
    }

    @Test
    void getReceiptWorksWithNoIngredients() {
        String bunName = data.getName();
        burger.setBuns(bun);
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(1f);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(bunName));
        assertTrue(receipt.contains("Price:"));
    }

    @Test
    void getReceiptCallsMethods() {
        burger.setBuns(bun);
        when(bun.getName()).thenReturn("bun");
        when(bun.getPrice()).thenReturn(1f);

        burger.getReceipt();

        verify(bun, times(2)).getName();
    }
}