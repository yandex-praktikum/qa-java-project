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
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("Проверка setBuns")
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    @DisplayName("Проверка addIngredient")
    public void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertTrue(burger.ingredients.contains(ingredient1));
    }

    @Test
    @DisplayName("Проверка removeIngredient")
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);

        assertFalse(burger.ingredients.contains(ingredient1));
        assertTrue(burger.ingredients.contains(ingredient2));
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    @DisplayName("Проверка moveIngredient")
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);

        assertEquals(ingredient1, burger.ingredients.get(1));
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Проверка исключения при удалении из пустого списка ингредиентов")
    public void removeIngredientWithEmptyIndexShouldThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.removeIngredient(0);
        }, "Должно быть выброшено исключение при удалении из пустого списка");
    }

    @Test
    @DisplayName("Проверка исключения при перемещении несуществующего ингредиента")
    public void moveNullIngredientShouldThrowException() {
        burger.addIngredient(ingredient1);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.moveIngredient(2, 0);
        }, "Должно быть выброшено исключение при перемещении несуществующего ингредиента");
    }

    @Test
    @DisplayName("Проверка getPrice")
    public void testGetPrice() {
        when(bun.getPrice()).thenReturn(988.0f);
        when(ingredient1.getPrice()).thenReturn(15.0f);
        when(ingredient2.getPrice()).thenReturn(1337.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = 988.0f * 2 + 15.0f + 1337.0f;
        assertEquals(expectedPrice, burger.getPrice());
    }

    @Test
    @DisplayName("Проверка getReceipt")
    public void testGetReceipt() {
        Bun bun1 = new Bun("Краторная булка N-200i", 1255f);
        Ingredient ingr1 = new Ingredient(IngredientType.FILLING, "Говяжий метеорит (отбивная)", 3000.0f);
        Ingredient ingr2 = new Ingredient(IngredientType.SAUCE, "Соус традиционный галактический", 15.0f);

        burger.setBuns(bun1);
        burger.addIngredient(ingr1);
        burger.addIngredient(ingr2);

        String separator = System.lineSeparator();

        String expectedReceipt =
                "(==== Краторная булка N-200i ====)" + separator +
                "= filling Говяжий метеорит (отбивная) =" + separator +
                "= sauce Соус традиционный галактический =" + separator +
                "(==== Краторная булка N-200i ====)" + separator + separator +
                "Price: 5525,000000" + separator;

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}