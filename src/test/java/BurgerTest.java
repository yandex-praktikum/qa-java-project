import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredient1;
    private Ingredient ingredient2;

    @BeforeEach
    public void setUp() {
        burger = new Burger();

        // Мок бургера
        bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Black Bun");
        when(bunMock.getPrice()).thenReturn(3.0f);

        // Мок ингредиентов
        ingredient1 = mock(Ingredient.class);
        when(ingredient1.getName()).thenReturn("Tomato");
        when(ingredient1.getPrice()).thenReturn(0.8f);
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);

        ingredient2 = mock(Ingredient.class);
        when(ingredient2.getName()).thenReturn("Ketchup");
        when(ingredient2.getPrice()).thenReturn(0.5f);
        when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);
    }

    @Test
    @DisplayName("Установка булки")
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    @DisplayName("Добавление ингредиента")
    public void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Удаление ингредиента по индексу")
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    @DisplayName("Перемещение ингредиентов")
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    @DisplayName("Расчёт цены с булкой и ингредиентами")
    public void testGetPrice() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expected = 3.0f * 2 + 0.8f + 0.5f;

        assertEquals(expected, burger.getPrice(), 0.001f);
    }

    @ParameterizedTest(name = "Булка: {0}, Цена булки: {1}, Ингредиенты: {2}, Ожидаемая цена: {3}")
    @CsvSource({
            "White Bun, 2.0, 0.5:1.0, 5.5",
            "Sesame Bun, 1.5, 0.3:0.7:1.2, 5.2"
    })
    @DisplayName("Параметризованный тест расчёта цены")
    public void testPriceParameterized(String bunName, float bunPrice, String ingredientPricesStr, float expectedPrice) {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);

        burger.setBuns(bun);

        if (!ingredientPricesStr.isEmpty()) {
            String[] prices = ingredientPricesStr.split(":");
            for (int i = 0; i < prices.length; i++) {
                float price = Float.parseFloat(prices[i]);
                Ingredient ing = mock(Ingredient.class);
                when(ing.getPrice()).thenReturn(price);
                when(ing.getType()).thenReturn(IngredientType.FILLING);
                when(ing.getName()).thenReturn("Ingredient" + i);
                burger.addIngredient(ing);
            }
        }

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    @DisplayName("Генерация чека")
    public void testGetReceipt() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("==== Black Bun ===="));
        assertTrue(receipt.contains("= filling Tomato ="));
        assertTrue(receipt.contains("= sauce Ketchup ="));
        assertTrue(receipt.contains("Price:"));
    }
}
