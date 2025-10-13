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
    private Ingredient mockIngredientOne;

    @Mock
    private Ingredient mockIngredientTwo;

    private Burger burger;

    @Before
    public void setUp(){
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertSame("Булочка должна устанавливаться правильно", mockBun, burger.bun);
    }
    @Test
    public void testAddIngredientIncreasesListSize() {
        burger.addIngredient(mockIngredientOne);
        assertEquals("Ingredients list should have 1 element", 1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        burger.addIngredient(mockIngredientOne);
        assertSame("Added ingredient should match", mockIngredientOne, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientDecreasesListSize() {
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);

        burger.removeIngredient(0);
        assertEquals("Ingredients list should have 1 element after removal", 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesCorrectIngredient() {
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);

        burger.removeIngredient(0);
        assertSame("Remaining ingredient should be the second one", mockIngredientTwo, burger.ingredients.get(0));
    }
    @Test
    public void testMoveIngredientChangesOrder() {
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);

        burger.moveIngredient(0, 1);

        assertSame("After move: first element should be mockIngredientTwo", mockIngredientTwo, burger.ingredients.get(0));
    }
    @Test
    public void testMoveIngredientChangesOrderSecondElement() {
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);

        burger.moveIngredient(0, 1);

        assertSame("After move: second element should be mockIngredientOne", mockIngredientOne, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientMaintainsListSize() {
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);

        burger.moveIngredient(0, 1);

        assertEquals("Ingredients list should still have 2 elements", 2, burger.ingredients.size());
    }

    @Test
    public void testGetPriceWithIngredientsCallsBunGetPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientOne);

        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientOne.getPrice()).thenReturn(50.0f);

        burger.getPrice();

        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void testGetPriceWithIngredientsCallsIngredientGetPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientOne);

        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientOne.getPrice()).thenReturn(50.0f);

        burger.getPrice();

        verify(mockIngredientOne).getPrice();
    }

    @Test
    public void testGetPriceWithIngredientsCalculatesCorrectly() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);

        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientOne.getPrice()).thenReturn(50.0f);
        when(mockIngredientTwo.getPrice()).thenReturn(75.0f);

        float expectedPrice = (100.0f * 2) + 50.0f + 75.0f;
        float actualPrice = burger.getPrice();

        assertEquals("Price should be calculated correctly", expectedPrice, actualPrice, 0.001f);
    }

    @Test
    public void testGetPriceWithoutIngredientsCallsBunGetPrice() {
        burger.setBuns(mockBun);

        when(mockBun.getPrice()).thenReturn(100.0f);

        burger.getPrice();

        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void testGetPriceWithoutIngredientsCalculatesCorrectly() {
        burger.setBuns(mockBun);

        when(mockBun.getPrice()).thenReturn(100.0f);

        float expectedPrice = 100.0f * 2;
        float actualPrice = burger.getPrice();

        assertEquals("Price should be only buns price when no ingredients", expectedPrice, actualPrice, 0.001f);
    }
    @Test
    public void testGetReceiptWithSauceIngredient() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientOne);

        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientOne.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientOne.getName()).thenReturn("Test Sauce");
        when(mockIngredientOne.getPrice()).thenReturn(50.0f);

        String receipt = burger.getReceipt();

        String expectedReceipt = "(==== Test Bun ====)\n" +
                "= sauce Test Sauce =\n" +
                "(==== Test Bun ====)\n\n" +
                "Price: 250,000000\n";

        assertEquals("Receipt should have correct format with sauce ingredient",
                expectedReceipt, receipt.replace("\r\n", "\n"));
    }


    @Test
    public void testGetReceiptWithFillingIngredient() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientOne);

        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientOne.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientOne.getName()).thenReturn("Test Filling");
        when(mockIngredientOne.getPrice()).thenReturn(50.0f);

        String receipt = burger.getReceipt();

        String expectedReceipt = "(==== Test Bun ====)\n" +
                "= filling Test Filling =\n" +
                "(==== Test Bun ====)\n\n" +
                "Price: 250,000000\n";

        assertEquals("Receipt should have correct format with filling ingredient",
                expectedReceipt, receipt.replace("\r\n", "\n"));
    }


    @Test
    public void testGetReceiptWithNoIngredients() {
        burger.setBuns(mockBun);

        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockBun.getPrice()).thenReturn(100.0f);

        String receipt = burger.getReceipt();

        String expectedReceipt = "(==== Test Bun ====)\n" +
                "(==== Test Bun ====)\n\n" +
                "Price: 200,000000\n";

        assertEquals("Receipt should have correct format without ingredients",
                expectedReceipt, receipt.replace("\r\n", "\n"));
    }


    @Test
    public void testGetReceiptWithMultipleIngredients() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);

        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientOne.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientOne.getName()).thenReturn("Hot Sauce");
        when(mockIngredientOne.getPrice()).thenReturn(50.0f);
        when(mockIngredientTwo.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientTwo.getName()).thenReturn("Cutlet");
        when(mockIngredientTwo.getPrice()).thenReturn(75.0f);

        String receipt = burger.getReceipt();

        String expectedReceipt = "(==== Test Bun ====)\n" +
                "= sauce Hot Sauce =\n" +
                "= filling Cutlet =\n" +
                "(==== Test Bun ====)\n\n" +
                "Price: 325,000000\n";

        assertEquals("Receipt should have correct format with multiple ingredients",
                expectedReceipt, receipt.replace("\r\n", "\n"));
    }


}