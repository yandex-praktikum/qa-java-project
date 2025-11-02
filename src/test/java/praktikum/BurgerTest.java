package praktikum;

import org.junit.Before;
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

    @Mock
    private Ingredient mockIngredient3;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertNotNull("Bun should be set", burger.bun);
        assertEquals("Set bun should match", mockBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals("Ingredients list should have 1 item", 1, burger.ingredients.size());
        assertTrue("Ingredients should contain added ingredient", burger.ingredients.contains(mockIngredient1));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);

        assertEquals("Ingredients list should have 1 item after removal", 1, burger.ingredients.size());
        assertFalse("Ingredients should not contain removed ingredient", burger.ingredients.contains(mockIngredient1));
        assertTrue("Ingredients should contain remaining ingredient", burger.ingredients.contains(mockIngredient2));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        // Move ingredient from index 0 to index 2
        burger.moveIngredient(0, 2);

        assertEquals("First ingredient should be moved", mockIngredient2, burger.ingredients.get(0));
        assertEquals("Second ingredient should be moved", mockIngredient3, burger.ingredients.get(1));
        assertEquals("Moved ingredient should be at new position", mockIngredient1, burger.ingredients.get(2));
    }

    @Test
    public void testGetPrice() {
        // Настраиваем заглушки только для этого теста
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        when(mockIngredient2.getPrice()).thenReturn(30.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Bun price * 2 + ingredient1 price + ingredient2 price
        float expectedPrice = (100.0f * 2) + 50.0f + 30.0f;

        assertEquals("Price calculation should be correct", expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceipt() {
        // Настраиваем заглушки только для этого теста
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getName()).thenReturn("cheese");
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("ketchup");
        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();

        assertNotNull("Receipt should not be null", receipt);
        assertTrue("Receipt should contain bun name", receipt.contains("black bun"));
        assertTrue("Receipt should contain ingredient names", receipt.contains("cheese"));
        assertTrue("Receipt should contain ingredient names", receipt.contains("ketchup"));
        assertTrue("Receipt should contain price", receipt.contains("Price:"));
    }

    @Test
    public void testGetPriceWithNoIngredients() {
        // Настраиваем заглушку только для этого теста
        when(mockBun.getPrice()).thenReturn(100.0f);

        burger.setBuns(mockBun);

        float expectedPrice = 100.0f * 2; // Only bun price * 2

        assertEquals("Price should be only bun price when no ingredients", expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceiptFormat() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getName()).thenReturn("cheese");
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();

        // Проверяем полный формат чека
        assertTrue("Receipt should contain top bun", receipt.contains("(==== black bun ====)"));
        assertTrue("Receipt should contain ingredient line", receipt.contains("= filling cheese ="));
        assertTrue("Receipt should contain bottom bun", receipt.contains("(==== black bun ====)"));
        assertTrue("Receipt should contain price", receipt.contains("Price:"));
        // Не проверяем точную цену, только что она есть
    }

    @Test
    public void testGetReceiptWithMultipleIngredients() {
        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(200.0f);
        when(mockIngredient1.getName()).thenReturn("hot sauce");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(100.0f);
        when(mockIngredient2.getName()).thenReturn("cutlet");
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getPrice()).thenReturn(150.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();

        assertTrue("Receipt should contain sauce", receipt.contains("sauce hot sauce"));
        assertTrue("Receipt should contain filling", receipt.contains("filling cutlet"));
        assertTrue("Receipt should contain total price", receipt.contains("Price:"));
        // Не проверяем точную цену, только что она есть
    }

    @Test
    public void testMoveIngredientToSamePosition() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Перемещаем на ту же позицию
        burger.moveIngredient(0, 0);

        assertEquals("First ingredient should stay", mockIngredient1, burger.ingredients.get(0));
        assertEquals("Second ingredient should stay", mockIngredient2, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientInvalidIndex() {
        burger.addIngredient(mockIngredient1);
        burger.removeIngredient(5); // Невалидный индекс
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientInvalidIndex() {
        burger.addIngredient(mockIngredient1);
        burger.moveIngredient(0, 5); // Невалидный индекс
    }
}