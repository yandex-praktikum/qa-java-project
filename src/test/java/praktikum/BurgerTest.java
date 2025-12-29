package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BurgerTest {

    private Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient1;

    @Mock
    Ingredient ingredient2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    void testGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        when(bun.getPrice()).thenReturn(1255f); // Краторная булка
        when(ingredient1.getPrice()).thenReturn(4142f); // Сыр с астероидной плесенью

        float price = burger.getPrice();

        assertEquals(6652, price); // 1255*2 + 4142 = 6652
    }

    @Test
    void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        when(bun.getName()).thenReturn("Краторная булка N-200i");
        when(bun.getPrice()).thenReturn(1255f);
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient1.getName()).thenReturn("Сыр с астероидной плесенью");

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Краторная булка N-200i"));
        assertTrue(receipt.contains("Сыр с астероидной плесенью"));
    }

    @Test
    void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }
}