package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Mock
    private Ingredient secondIngredient;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("setBuns устанавливает булочку")
    void setBunsShouldSetBun() {
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    @DisplayName("addIngredient добавляет ингредиент")
    void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    @DisplayName("removeIngredient удаляет ингредиент по индексу")
    void removeIngredientShouldRemoveIngredient() {
        burger.addIngredient(ingredient);

        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    @DisplayName("moveIngredient меняет местами ингредиенты")
    void moveIngredientShouldSwapIngredients() {
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(secondIngredient, burger.ingredients.get(0));
        assertEquals(ingredient, burger.ingredients.get(1));
    }

    @Test
    @DisplayName("ингредиенты сохраняют порядок добавления")
    void ingredientsShouldKeepOrder() {
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        assertEquals(ingredient, burger.ingredients.get(0));
        assertEquals(secondIngredient, burger.ingredients.get(1));
    }

    @Test
    @DisplayName("getPrice возвращает цену бургера с одним ингредиентом")
    void getPriceShouldCalculateCorrectPrice() {
        int bunPrice = 100;
        int ingredientPrice = 50;

        when(bun.getPrice()).thenReturn((float) bunPrice);
        when(ingredient.getPrice()).thenReturn((float) ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        int expectedPrice = bunPrice * 2 + ingredientPrice;

        assertEquals(expectedPrice, (int) burger.getPrice());
    }

    @Test
    @DisplayName("getPrice считает цену всех ингредиентов")
    void getPriceShouldSumAllIngredients() {
        int bunPrice = 100;
        int firstIngredientPrice = 30;
        int secondIngredientPrice = 40;

        when(bun.getPrice()).thenReturn((float) bunPrice);
        when(ingredient.getPrice()).thenReturn((float) firstIngredientPrice);
        when(secondIngredient.getPrice()).thenReturn((float) secondIngredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        int expectedPrice = bunPrice * 2 + firstIngredientPrice + secondIngredientPrice;

        assertEquals(expectedPrice, (int) burger.getPrice());
    }

    @Test
    @DisplayName("getReceipt содержит название булки и ингредиента")
    void getReceiptShouldContainNames() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn((float) 100);

        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getPrice()).thenReturn((float) 50);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("black bun"));
        assertTrue(receipt.contains("cutlet"));
    }

    @Test
    @DisplayName("getReceipt содержит все ингредиенты")
    void getReceiptShouldContainAllIngredients() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn((float) 100);

        when(ingredient.getName()).thenReturn("hot sauce");
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getPrice()).thenReturn((float) 20);

        when(secondIngredient.getName()).thenReturn("cutlet");
        when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredient.getPrice()).thenReturn((float) 50);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("= sauce hot sauce ="));
        assertTrue(receipt.contains("= filling cutlet ="));
    }

    @Test
    @DisplayName("getReceipt содержит строку с ценой")
    void getReceiptShouldContainPriceLine() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn((float) 100);

        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getPrice()).thenReturn((float) 50);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Price:"));
    }

    @Test
    @DisplayName("getReceipt использует название булочки два раза")
    void getReceiptShouldUseBunNameTwice() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn((float) 100);

        burger.setBuns(bun);

        burger.getReceipt();

        verify(bun, times(2)).getName();
    }
}