package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;

@RunWith(MockitoJUnitRunner.Silent.class)  // Используем Silent режим
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    @Before
    public void setUp() {
        burger = new Burger();

        lenient().when(mockBun.getName()).thenReturn("mock bun");
        lenient().when(mockBun.getPrice()).thenReturn(100f);

        lenient().when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        lenient().when(mockIngredient1.getName()).thenReturn("mock sauce");
        lenient().when(mockIngredient1.getPrice()).thenReturn(50f);

        lenient().when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        lenient().when(mockIngredient2.getName()).thenReturn("mock filling");
        lenient().when(mockIngredient2.getPrice()).thenReturn(75f);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals("Bun should be set correctly", mockBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        assertEquals("Should have 2 ingredients", 2, burger.ingredients.size());
        assertEquals("First ingredient should be mockIngredient1", mockIngredient1, burger.ingredients.get(0));
        assertEquals("Second ingredient should be mockIngredient2", mockIngredient2, burger.ingredients.get(1));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        lenient().when(mockIngredient3.getType()).thenReturn(IngredientType.SAUCE);
        lenient().when(mockIngredient3.getName()).thenReturn("another sauce");
        lenient().when(mockIngredient3.getPrice()).thenReturn(30f);

        assertEquals("Should have 3 ingredients before removal", 3, burger.ingredients.size());

        burger.removeIngredient(1);

        assertEquals("Should have 2 ingredients after removal", 2, burger.ingredients.size());
        assertEquals("First ingredient should still be mockIngredient1", mockIngredient1, burger.ingredients.get(0));
        assertEquals("Second ingredient should be mockIngredient3", mockIngredient3, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        lenient().when(mockIngredient3.getType()).thenReturn(IngredientType.SAUCE);
        lenient().when(mockIngredient3.getName()).thenReturn("another sauce");
        lenient().when(mockIngredient3.getPrice()).thenReturn(30f);

        burger.moveIngredient(2, 1);

        assertEquals("Should still have 3 ingredients", 3, burger.ingredients.size());
        assertEquals("Position 0 should be mockIngredient1", mockIngredient1, burger.ingredients.get(0));
        assertEquals("Position 1 should be mockIngredient3 (moved)", mockIngredient3, burger.ingredients.get(1));
        assertEquals("Position 2 should be mockIngredient2", mockIngredient2, burger.ingredients.get(2));
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 100f * 2 + 50f + 75f;

        assertEquals("Price should be calculated correctly", expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetPriceWithNoIngredients() {
        burger.setBuns(mockBun);

        float expectedPrice = 100f * 2;

        assertEquals("Price should be bun price * 2 with no ingredients", expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();

        String expectedBunName = mockBun.getName();
        String expectedFirstIngredientType = mockIngredient1.getType().toString().toLowerCase();
        String expectedFirstIngredientName = mockIngredient1.getName();
        String expectedSecondIngredientType = mockIngredient2.getType().toString().toLowerCase();
        String expectedSecondIngredientName = mockIngredient2.getName();
        float expectedPrice = mockBun.getPrice() * 2 + mockIngredient1.getPrice() + mockIngredient2.getPrice();

        String expectedStart = String.format("(==== %s ====)%n", expectedBunName);
        String expectedIngredient1Line = String.format("= %s %s =%n", expectedFirstIngredientType, expectedFirstIngredientName);
        String expectedIngredient2Line = String.format("= %s %s =%n", expectedSecondIngredientType, expectedSecondIngredientName);
        String expectedEnd = String.format("(==== %s ====)%n", expectedBunName);
        String expectedPriceLine = String.format("%nPrice: %f%n", expectedPrice);

        String expectedFullReceipt = expectedStart +
                expectedIngredient1Line +
                expectedIngredient2Line +
                expectedEnd +
                expectedPriceLine;

        assertEquals("Full receipt should match expected format", expectedFullReceipt, receipt);
    }

    @Test
    public void testGetReceiptWithNoIngredients() {
        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();

        String expectedBunName = mockBun.getName();
        float expectedPrice = mockBun.getPrice() * 2;

        String expectedStart = String.format("(==== %s ====)%n", expectedBunName);
        String expectedEnd = String.format("(==== %s ====)%n", expectedBunName);
        String expectedPriceLine = String.format("%nPrice: %f%n", expectedPrice);

        String expectedFullReceipt = expectedStart + expectedEnd + expectedPriceLine;

        assertEquals("Full receipt should match expected format", expectedFullReceipt, receipt);
    }

    @Test
    public void testIntegrationWithRealBunAndMockedIngredients() {
        Bun realBun = new Bun("real bun", 150f);

        burger.setBuns(realBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 150f * 2 + 50f + 75f;

        assertEquals("Price should be calculated correctly with mixed real and mock objects",
                expectedPrice, burger.getPrice(), 0.001);
    }
}