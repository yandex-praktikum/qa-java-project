package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for basic Burger class methods.
 * Tests basic functionality without parameterization.
 */
public class BurgerCoreTest extends BaseBurgerTest {

    @Test
    public void testSetBunsCorrectlyAssignsBun() {
        burger.setBuns(blackBun);

        assertNotNull("Bun should be set", burger.bun);
        assertEquals("Bun name should match",
                "black bun", burger.bun.getName());
    }

    @Test
    public void testAddIngredientIncreasesListSize() {
        burger.addIngredient(cutlet);

        assertEquals("Ingredients list should contain 1 element",
                1, burger.ingredients.size());
        assertTrue("List should contain added ingredient",
                burger.ingredients.contains(cutlet));
    }

    @Test
    public void testRemoveIngredientDecreasesListSize() {
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);

        burger.removeIngredient(0);

        assertEquals("After removal should have 1 ingredient",
                1, burger.ingredients.size());
        assertFalse("Removed ingredient should not be present",
                burger.ingredients.contains(cutlet));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithInvalidIndexThrowsException() {
        burger.addIngredient(cutlet);
        burger.removeIngredient(5); // Invalid index
    }

    @Test
    public void testMoveIngredientChangesPositionsCorrectly() {
        // Add three ingredients
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);
        burger.addIngredient(sausage);

        // Move sausage from position 2 to position 0
        burger.moveIngredient(2, 0);

        // Check new order: sausage, cutlet, dinosaur
        assertEquals("First position should be sausage",
                sausage, burger.ingredients.get(0));
        assertEquals("Second position should be cutlet",
                cutlet, burger.ingredients.get(1));
        assertEquals("Third position should be dinosaur",
                dinosaur, burger.ingredients.get(2));
    }

    @Test
    public void testGetPriceWithMultipleIngredients() {
        burger.setBuns(blackBun);
        burger.addIngredient(cutlet);
        burger.addIngredient(dinosaur);
        burger.addIngredient(hotSauce);

        float expectedPrice = 100.0f * 2 + 100.0f + 200.0f + 100.0f; // 200 + 100 + 200 + 100 = 600
        assertEquals("Price should be calculated correctly",
                expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test(expected = NullPointerException.class)
    public void testGetPriceWithoutBunThrowsException() {
        burger.getPrice();
    }

    @Test
    public void testGetReceiptFormatsCorrectly() {
        burger.setBuns(blackBun);
        burger.addIngredient(cutlet);
        burger.addIngredient(hotSauce);

        String receipt = burger.getReceipt();

        assertTrue("Receipt should contain bun name", receipt.contains("black bun"));
        assertTrue("Receipt should contain cutlet", receipt.contains("cutlet"));
        assertTrue("Receipt should contain hot sauce", receipt.contains("hot sauce"));
        assertTrue("Receipt should contain Price label", receipt.contains("Price:"));

        // Extract and verify price
        float extractedPrice = extractPriceFromReceipt(receipt);
        float expectedPrice = 400.0f; // 200 (bun*2) + 100 (cutlet) + 100 (hot sauce)
        assertEquals("Price in receipt should be correct",
                expectedPrice, extractedPrice, 0.001f);
    }

    @Test
    public void testReceiptStructureWithTopBun() {
        burger.setBuns(blackBun);
        String receipt = burger.getReceipt();

        assertTrue("Receipt should start with top bun",
                receipt.startsWith("(==== black bun ====)"));
    }

    @Test
    public void testReceiptContainsIngredientTypeLowerCase() {
        burger.setBuns(blackBun);
        burger.addIngredient(cutlet);
        burger.addIngredient(hotSauce);

        String receipt = burger.getReceipt();

        assertTrue("Receipt should contain ingredient type in lowercase",
                receipt.contains("filling") || receipt.contains("sauce"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetReceiptWithoutBunThrowsException() {
        burger.getReceipt();
    }
}