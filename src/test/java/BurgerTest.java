import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;
import praktikum.Burger;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    @BeforeEach
    void setUp() {
        burger = new Burger();

        // Моки
        bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Mocked Bun");
        when(bunMock.getPrice()).thenReturn(100f);

        ingredientMock1 = mock(Ingredient.class);
        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock1.getName()).thenReturn("mocked lettuce");
        when(ingredientMock1.getPrice()).thenReturn(20f);

        ingredientMock2 = mock(Ingredient.class);
        when(ingredientMock2.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock2.getName()).thenReturn("mocked ketchup");
        when(ingredientMock2.getPrice()).thenReturn(15f);
    }

    @Test
    void testSetBuns() {
        // Используем мок
        burger.setBuns(bunMock);
        // Проверка через getter
        assertEquals(bunMock, burger.bun);
        assertEquals("Mocked Bun", burger.bun.getName());
        assertEquals(100f, burger.bun.getPrice());
    }

    @Test
    void testAddIngredientsWithMocks() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));
        assertEquals(ingredientMock2, burger.ingredients.get(1));
    }

    @Test
    void testGetPriceWithMocks() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expectedPrice = 100f * 2 + 20f + 15f;

        assertEquals(expectedPrice, burger.getPrice());
    }

    @Test
    void testGetReceiptWithMocks() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== Mocked Bun ====)"));
        assertTrue(receipt.contains("= filling mocked lettuce ="));
        assertTrue(receipt.contains("= sauce mocked ketchup ="));
        assertTrue(receipt.contains(String.format("Price: %f", burger.getPrice())));
    }

    // Параметризованный тест для различных ингредиентов
    @ParameterizedTest
    @CsvSource({
            "FILLING, Lettuce, 10",
            "SAUCE, Mayonnaise, 8",
            "FILLING, Tomato, 12"
    })
    void testIngredientParameterization(String typeStr, String name, float price) {
        IngredientType type = IngredientType.valueOf(typeStr);
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice());
    }

    // Дополнительные тесты для методов удаления и перемещения
    @Test
    void testRemoveIngredientValidIndex() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        assertEquals(2, burger.ingredients.size());

        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    void testRemoveIngredientInvalidIndex() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.removeIngredient(5);
        });
    }

    @Test
    void testMoveIngredientValidIndices() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        // Перемещаем первый элемент на вторую позицию
        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

    @Test
    void testMoveIngredientInvalidIndices() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        // Некорректный исходный индекс
        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.moveIngredient(5, 0);
        });
        // Некорректный целевой индекс
        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.moveIngredient(0, 5);
        });
    }
}