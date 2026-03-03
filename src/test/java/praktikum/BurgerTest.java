package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BurgerTest {
    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    private Burger burger;

    @BeforeEach
    void init() {
        burger = new Burger();
        burger.setBuns(bunMock);
    }

    @Test
    @DisplayName("Проверка, что если нет булочки, нет возможности высчитать цену")
    public void testFailedGetPriceWhenNoBunSetForBurger() {
        burger.setBuns(null);
        assertThrows(NullPointerException.class, () -> burger.getPrice());
    }

    @Test
    @DisplayName("Проверка, что если нет булочки, нет возможности написать рецепт")
    public void testFailedGetReceiptWhenNoBunSetForBurger() {
        burger.setBuns(null);
        assertThrows(NullPointerException.class, () -> burger.getReceipt());
    }

    @Test
    @DisplayName("Проверка, что цена бургера правильно считается")
    public void testCorrectPriceWhenBunAndInrgdsSetPrice() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        when(bunMock.getPrice()).thenReturn(100f); //stub
        when(ingredientMock1.getPrice()).thenReturn(150f);
        when(ingredientMock2.getPrice()).thenReturn(80f);

        assertEquals(100f*2+150f+80f, burger.getPrice());
    }

    @Test
    @DisplayName("Проверка, что рецепт бургера содержит правильные названия ингридиентов")
    public void testCorrectReceiptWhenBunAndInrgdsHasName() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        when(bunMock.getPrice()).thenReturn(100f); //stub
        when(ingredientMock1.getPrice()).thenReturn(150f);
        when(ingredientMock2.getPrice()).thenReturn(80f);
        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn("Сыр");
        when(ingredientMock2.getName()).thenReturn("Кетчуп");
        when(bunMock.getName()).thenReturn("Белая");

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Белая"));
        assertTrue(receipt.contains("Сыр"));
        assertTrue(receipt.contains("Кетчуп"));
    }

    @Test
    @DisplayName("Проверка, что праивльно работает удаление ингридиента")
    public void testCorrectReceiptWhenRemoveIngredientFromBurger() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        when(bunMock.getPrice()).thenReturn(100f); //stub
        when(ingredientMock1.getPrice()).thenReturn(150f);
        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock1.getName()).thenReturn("Сыр");
        when(bunMock.getName()).thenReturn("Белая");
        burger.removeIngredient(1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Белая"));
        assertTrue(receipt.contains("Сыр"));
        assertFalse(receipt.contains("Кетчуп"));
    }

    @Test
    @DisplayName("Проверка, что рецепт бургера содержит правильный порядок ингридиентов")
    public void testCorrectReceiptWhenMoveIngredient() {
        Database database = new Database();
        Bun bun = database.availableBuns().get(0);
        Ingredient ing1 = database.availableIngredients().get(0);
        Ingredient ing2 = database.availableIngredients().get(1);
        burger.setBuns(bun);
        burger.addIngredient(ing1);
        burger.addIngredient(ing2);
        burger.moveIngredient(0, 1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains(bun.getName()));
        assertTrue(receipt.contains(ing2.getName()));
        receipt = receipt.substring(receipt.indexOf(ing2.getName()));
        assertTrue(receipt.contains(ing1.getName()));
    }
}