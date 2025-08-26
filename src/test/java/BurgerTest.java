import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock;

    @BeforeEach
    void setUp() {
        burger = new Burger();
        bunMock = mock(Bun.class);
        ingredientMock = mock(Ingredient.class);
    }

    @Test
    void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    void testAddIngredient() {
        burger.addIngredient(ingredientMock);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    void testRemoveIngredient() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    void testMoveIngredient() {
        Ingredient anotherIngredient = mock(Ingredient.class);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(anotherIngredient);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock, burger.ingredients.get(1));
        assertEquals(anotherIngredient, burger.ingredients.get(0));
    }

    @Test
    void testGetPriceSingleIngredient() {
        when(bunMock.getPrice()).thenReturn(2.0f);
        when(ingredientMock.getPrice()).thenReturn(1.5f);
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        assertEquals(5.5f, burger.getPrice());
    }

    @Test
    void testGetPriceNoIngredients() {
        when(bunMock.getPrice()).thenReturn(2.0f);
        burger.setBuns(bunMock);
        assertEquals(4.0f, burger.getPrice());
    }

    @Test
    void testGetPriceMultipleIngredients() {
        when(bunMock.getPrice()).thenReturn(2.0f);
        Ingredient ing1 = mock(Ingredient.class);
        Ingredient ing2 = mock(Ingredient.class);
        when(ing1.getPrice()).thenReturn(1.0f);
        when(ing2.getPrice()).thenReturn(0.5f);

        burger.setBuns(bunMock);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);

        assertEquals(5.5f, burger.getPrice());
    }

    @Test
    void testGetReceipt() {
        when(bunMock.getName()).thenReturn("White Bun");
        when(bunMock.getPrice()).thenReturn(2.0f);
        when(ingredientMock.getName()).thenReturn("Cheese");
        when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock.getPrice()).thenReturn(1.5f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt().toLowerCase();
        assertTrue(receipt.contains("white bun"));
        assertTrue(receipt.contains("cheese"));
        assertTrue(receipt.contains("filling"));
        assertTrue(receipt.contains("price: 5.5"));
    }

    @ParameterizedTest
    @CsvSource({
            "2.0, 1.5, 5.5",
            "1.0, 2.0, 4.0",
            "3.0, 0.5, 6.5",
            "2.5, 0.0, 5.0"  // проверка без ингредиентов
    })
    void testGetPriceParameterized(float bunPrice, float ingredientPrice, float expected) {
        when(bunMock.getPrice()).thenReturn(bunPrice);
        Ingredient ing = mock(Ingredient.class);
        when(ing.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bunMock);
        burger.addIngredient(ing);

        assertEquals(expected, burger.getPrice());
    }

    @Test
    void testReceiptEmptyBurger() {
        when(bunMock.getName()).thenReturn("Plain Bun");
        when(bunMock.getPrice()).thenReturn(1.0f);
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt().toLowerCase();
        assertTrue(receipt.contains("plain bun"));
        assertTrue(receipt.contains("price: 2.0"));
    }
}
