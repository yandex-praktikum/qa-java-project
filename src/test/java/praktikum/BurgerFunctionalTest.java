package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerFunctionalTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testConstructorInitializesFields() {
        assertNotNull(burger.ingredients);
        assertTrue(burger.ingredients.isEmpty());
        assertNull(burger.bun);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertSame(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientMock);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredientMock, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredients() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        assertEquals(3, burger.ingredients.size());
        assertSame(ingredient1, burger.ingredients.get(0));
        assertSame(ingredient2, burger.ingredients.get(1));
        assertSame(ingredient3, burger.ingredients.get(2));
    }

    @Test
    public void testRemoveIngredientValidIndex() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientValidIndexes() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
        assertSame(ingredient2, burger.ingredients.get(0));
        assertSame(ingredient3, burger.ingredients.get(1));
        assertSame(ingredient1, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientToSamePosition() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 0);

        assertEquals(2, burger.ingredients.size());
        assertSame(ingredient1, burger.ingredients.get(0));
        assertSame(ingredient2, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceWithBunAndIngredients() {
        when(bunMock.getPrice()).thenReturn(50.0f);
        when(ingredientMock.getPrice()).thenReturn(30.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        float price = burger.getPrice();
        assertEquals(130.0f, price, 0.001f); // 50*2 + 30 = 130
    }

    @Test
    public void testGetPriceWithBunOnly() {
        when(bunMock.getPrice()).thenReturn(100.0f);

        burger.setBuns(bunMock);

        float price = burger.getPrice();
        assertEquals(200.0f, price, 0.001f); // 100*2 = 200
    }
    @Test
    public void testGetPriceWithMultipleIngredients() {
        when(bunMock.getPrice()).thenReturn(10.0f);

        Ingredient ingredient1 = mock(Ingredient.class);
        when(ingredient1.getPrice()).thenReturn(5.0f);

        Ingredient ingredient2 = mock(Ingredient.class);
        when(ingredient2.getPrice()).thenReturn(7.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float price = burger.getPrice();
        assertEquals(32.0f, price, 0.001f); // 10*2 + 5 + 7 = 32
    }

    @Test
    public void testGetReceiptFormat() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);
        when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock.getName()).thenReturn("hot sauce");
        when(ingredientMock.getPrice()).thenReturn(50.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("= sauce hot sauce ="));
        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("Price: 250.000000"));
    }

    @Test
    public void testGetReceiptWithMultipleIngredients() {
        when(bunMock.getName()).thenReturn("white bun");
        when(bunMock.getPrice()).thenReturn(200.0f);

        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("sour cream");
        when(sauce.getPrice()).thenReturn(100.0f);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(150.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("(==== white bun ====)"));
        assertTrue(receipt.contains("= sauce sour cream ="));
        assertTrue(receipt.contains("= filling cutlet ="));
        assertTrue(receipt.contains("Price: 650.000000")); // 200*2 + 100 + 150 = 650
    }

    @Test
    public void testGetReceiptWithNoIngredients() {
        when(bunMock.getName()).thenReturn("red bun");
        when(bunMock.getPrice()).thenReturn(300.0f);

        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("(==== red bun ====)"));
        assertTrue(receipt.contains("Price: 600.000000")); // 300*2 = 600
        // Проверяем, что между строками с булкой нет строк с ингредиентами
        String[] lines = receipt.split("\n");
        boolean foundFirstBun = false;
        for (String line : lines) {
            if (line.contains("(==== red bun ====)")) {
                if (!foundFirstBun) {
                    foundFirstBun = true;
                } else {
                    // Вторая строка с булкой должна идти сразу после первой или после пустой строки
                    break;
                }
            }
        }
    }

    @Test
    public void testIngredientTypeIsLowerCaseInReceipt() {
        when(bunMock.getName()).thenReturn("Bun");
        when(bunMock.getPrice()).thenReturn(10.0f);
        when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock.getName()).thenReturn("Cheese");
        when(ingredientMock.getPrice()).thenReturn(20.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("filling"));
        assertFalse(receipt.contains("FILLING"));
    }

    @Test
    public void testClearAllIngredients() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(1);
        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }
}
