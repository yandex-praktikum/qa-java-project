package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for edge cases and error handling.
 */
public class BurgerEdgeCasesTest extends BaseBurgerTest {

    @Test
    public void testGetReceiptWithEmptyBurger() {
        burger.setBuns(blackBun);
        String receipt = burger.getReceipt();

        assertNotNull("Receipt should not be null", receipt);
        assertFalse("Receipt should not be empty", receipt.isEmpty());
        assertTrue("Receipt should contain bun name",
                receipt.contains("black bun"));

        // Extract and verify price
        float extractedPrice = extractPriceFromReceipt(receipt);
        assertEquals("Price should be 200 for bun only",
                200.0f, extractedPrice, 0.001f);
    }

    @Test
    public void testMoveIngredientWithSingleElement() {
        burger.addIngredient(cutlet);

        burger.moveIngredient(0, 0);

        assertEquals("Should remain one element",
                1, burger.ingredients.size());
        assertEquals("Element should stay in place",
                cutlet, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientWithInvalidFromIndex() {
        burger.addIngredient(cutlet);
        burger.moveIngredient(2, 0); // Invalid from index
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientWithInvalidToIndex() {
        burger.addIngredient(cutlet);
        burger.moveIngredient(0, 2); // Invalid to index
    }

    @Test
    public void testPriceCalculationAccuracy() {
        burger.setBuns(whiteBun);
        burger.addIngredient(cutlet);
        burger.addIngredient(sourCream);

        // Exact calculation: 200*2 + 100 + 200 = 700
        float calculatedPrice = burger.getPrice();
        float expectedPrice = 700.0f;

        assertEquals("Price should be calculated accurately",
                expectedPrice, calculatedPrice, 0.000001f);
    }

    @Test
    public void testReceiptLineSeparatorConsistency() {
        burger.setBuns(blackBun);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();

        // Check that receipt contains line separators
        assertTrue("Receipt should contain line separators",
                receipt.contains("\n") || receipt.contains("\r\n"));
    }

    @Test
    public void testAddMultipleIngredientsAndVerifyOrder() {
        burger.setBuns(blackBun);
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);
        burger.addIngredient(hotSauce);

        String receipt = burger.getReceipt();

        // Check that ingredients appear in the order they were added
        int cutletIndex = receipt.indexOf("cutlet");
        int dinosaurIndex = receipt.indexOf("dinosaur");
        int hotSauceIndex = receipt.indexOf("hot sauce");

        assertTrue("cutlet should come before dinosaur", cutletIndex < dinosaurIndex);
        assertTrue("dinosaur should come before hot sauce", dinosaurIndex < hotSauceIndex);
    }
}