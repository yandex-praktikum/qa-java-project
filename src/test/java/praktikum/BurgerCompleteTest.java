package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerCompleteTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    @Before
    public void setUp() {
        burger = new Burger();

        when(bunMock.getName()).thenReturn("Complete Bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn("Complete Sauce");
        when(ingredientMock1.getPrice()).thenReturn(50.0f);

        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getName()).thenReturn("Complete Filling");
        when(ingredientMock2.getPrice()).thenReturn(75.0f);
    }

    @Test
    public void testAllMethods() {
        // 1. Конструктор
        assertNotNull(burger.ingredients);
        assertTrue(burger.ingredients.isEmpty());
        assertNull(burger.bun);

        // 2. setBuns
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);

        // 3. addIngredient
        burger.addIngredient(ingredientMock1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));

        // 4. add второй ингредиент
        burger.addIngredient(ingredientMock2);
        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(1));

        // 5. moveIngredient
        burger.moveIngredient(0, 1);
        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock1, burger.ingredients.get(1));

        // 6. getPrice
        float price = burger.getPrice();
        assertEquals(325.0f, price, 0.001f); // 100*2 + 50 + 75 = 325

        // 7. getReceipt
        String receipt = burger.getReceipt();
        assertNotNull(receipt);
        assertTrue(receipt.contains("Complete Bun"));
        assertTrue(receipt.contains("Complete Filling"));
        assertTrue(receipt.contains("Complete Sauce"));
        assertTrue(receipt.contains("Price: 325.000000"));

        // 8. removeIngredient
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));

        // 9. Новый getPrice после удаления
        price = burger.getPrice();
        assertEquals(250.0f, price, 0.001f); // 100*2 + 50 = 250

        // 10. Новый getReceipt после удаления
        receipt = burger.getReceipt();
        assertTrue(receipt.contains("Complete Sauce"));
        assertFalse(receipt.contains("Complete Filling"));
    }

    @Test
    public void testGetReceiptFormat() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        String receipt = burger.getReceipt();

        // Проверяем полный формат
        assertTrue(receipt.startsWith("(==== Complete Bun ====)\n"));
        assertTrue(receipt.contains("= sauce Complete Sauce =\n"));
        assertTrue(receipt.contains("(==== Complete Bun ====)\n"));
        assertTrue(receipt.endsWith("\nPrice: 250.000000\n") || receipt.contains("\nPrice: 250.000000\n"));
    }

    @Test
    public void testMultipleIngredientsOrderInReceipt() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String receipt = burger.getReceipt();

        // Проверяем порядок ингредиентов
        int sauceIndex = receipt.indexOf("= sauce Complete Sauce =");
        int fillingIndex = receipt.indexOf("= filling Complete Filling =");

        assertTrue(sauceIndex > 0);
        assertTrue(fillingIndex > 0);
        assertTrue(sauceIndex < fillingIndex); // Первый добавленный должен быть первым в чеке
    }

    @Test
    public void testPriceCalculationEdgeCases() {
        // Тест с разными ценами
        Burger testBurger = new Burger();

        Bun testBun = mock(Bun.class);
        when(testBun.getPrice()).thenReturn(0.0f);

        Ingredient freeIngredient = mock(Ingredient.class);
        when(freeIngredient.getPrice()).thenReturn(0.0f);

        Ingredient expensiveIngredient = mock(Ingredient.class);
        when(expensiveIngredient.getPrice()).thenReturn(1000.0f);

        testBurger.setBuns(testBun);
        testBurger.addIngredient(freeIngredient);
        testBurger.addIngredient(expensiveIngredient);

        float price = testBurger.getPrice();
        assertEquals(1000.0f, price, 0.001f); // 0*2 + 0 + 1000 = 1000
    }

    @Test
    public void testReceiptWithSpecialCharacters() {
        Burger testBurger = new Burger();

        Bun specialBun = mock(Bun.class);
        when(specialBun.getName()).thenReturn("Bun & Roll's Special");
        when(specialBun.getPrice()).thenReturn(123.45f);

        Ingredient specialIngredient = mock(Ingredient.class);
        when(specialIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(specialIngredient.getName()).thenReturn("Extra \"Cheesy\" Filling");
        when(specialIngredient.getPrice()).thenReturn(67.89f);

        testBurger.setBuns(specialBun);
        testBurger.addIngredient(specialIngredient);

        String receipt = testBurger.getReceipt();

        assertTrue(receipt.contains("Bun & Roll's Special"));
        assertTrue(receipt.contains("Extra \"Cheesy\" Filling"));
        assertTrue(receipt.contains("filling"));
    }
}
