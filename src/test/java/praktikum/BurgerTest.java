package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    @Mock
    private Ingredient thirdIngredient;

    @Test
    public void testSetBuns() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);

        assertSame(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredientsIncreasesSize() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void testAddMultipleIngredientsAddsCorrectFirstIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        assertSame(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testAddMultipleIngredientsAddsCorrectSecondIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        assertSame(secondIngredient, burger.ingredients.get(1));
    }

    @Test
    public void testAddMultipleIngredientsAddsCorrectThirdIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        assertSame(thirdIngredient, burger.ingredients.get(2));
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.removeIngredient(1);

        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientKeepsFirstIngredient() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.removeIngredient(1);

        assertSame(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientKeepsLastIngredient() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.removeIngredient(1);

        assertSame(thirdIngredient, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithInvalidIndex() {
        Burger burger = new Burger();
        burger.ingredients.add(firstIngredient);

        burger.removeIngredient(5);
    }

    @Test
    public void testMoveIngredientForwardChangesSize() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientForwardChangesFirstPosition() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.moveIngredient(0, 2);

        assertSame(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientForwardChangesSecondPosition() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.moveIngredient(0, 2);

        assertSame(thirdIngredient, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientForwardChangesLastPosition() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.moveIngredient(0, 2);

        assertSame(firstIngredient, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientBackwardChangesSize() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.moveIngredient(2, 0);

        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientBackwardChangesFirstPosition() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.moveIngredient(2, 0);

        assertSame(thirdIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientBackwardChangesSecondPosition() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.moveIngredient(2, 0);

        assertSame(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientBackwardChangesLastPosition() {
        Burger burger = new Burger();
        burger.ingredients.addAll(Arrays.asList(firstIngredient, secondIngredient, thirdIngredient));

        burger.moveIngredient(2, 0);

        assertSame(secondIngredient, burger.ingredients.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientWithInvalidFromIndex() {
        Burger burger = new Burger();
        burger.ingredients.add(firstIngredient);

        burger.moveIngredient(5, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientWithInvalidToIndex() {
        Burger burger = new Burger();
        burger.ingredients.add(firstIngredient);

        burger.moveIngredient(0, 5);
    }

    @RunWith(Parameterized.class)
    public static class GetPriceParameterizedTest {

        @Parameterized.Parameters
        public static Object[][] testData() {
            return new Object[][]{
                    {100.0f, new Float[]{50.0f, 30.0f}, 280.0f},
                    {50.0f, new Float[]{10.0f, 20.0f, 5.0f}, 135.0f},
                    {0.0f, new Float[]{}, 0.0f},
                    {25.5f, new Float[]{15.25f}, 66.25f}
            };
        }

        @Parameterized.Parameter(0)
        public float bunPrice;

        @Parameterized.Parameter(1)
        public Float[] ingredientPrices;

        @Parameterized.Parameter(2)
        public float expectedTotalPrice;

        @Test
        public void testGetPriceWithDifferentCombinations() {
            Bun bun = mock(Bun.class);
            when(bun.getPrice()).thenReturn(bunPrice);

            Burger burger = new Burger();
            burger.setBuns(bun);

            for (float ingredientPrice : ingredientPrices) {
                Ingredient ingredient = mock(Ingredient.class);
                when(ingredient.getPrice()).thenReturn(ingredientPrice);
                burger.addIngredient(ingredient);
            }

            float actualPrice = burger.getPrice();

            assertEquals(expectedTotalPrice, actualPrice, 0.001f);
        }
    }

    @Test
    public void testGetReceiptFormat() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(100.0f);

        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredient.getName()).thenReturn("hot sauce");
        when(firstIngredient.getPrice()).thenReturn(50.0f);

        when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredient.getName()).thenReturn("cutlet");
        when(secondIngredient.getPrice()).thenReturn(30.0f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        String expectedReceipt = "(==== white bun ====)\r\n" +
                "= sauce hot sauce =\r\n" +
                "= filling cutlet =\r\n" +
                "(==== white bun ====)\r\n" +
                "\r\n" +
                "Price: 280,000000\r\n";

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }

    @Test
    public void testGetReceiptWithOnlyBun() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(50.0f);

        Burger burger = new Burger();
        burger.setBuns(bun);

        String expectedReceipt = "(==== black bun ====)\r\n" +
                "(==== black bun ====)\r\n" +
                "\r\n" +
                "Price: 100,000000\r\n";

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }

    @Test
    public void testGetReceiptContainsCorrectPrice() {
        when(bun.getName()).thenReturn("test bun");
        when(bun.getPrice()).thenReturn(10.0f);

        Burger burger = new Burger();
        burger.setBuns(bun);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Price: 20,000000"));
    }

    @Test
    public void testGetReceiptCallsBunNameTwice() {
        when(bun.getName()).thenReturn("test bun");
        when(bun.getPrice()).thenReturn(10.0f);

        Burger burger = new Burger();
        burger.setBuns(bun);

        burger.getReceipt();

        verify(bun, times(2)).getName();
    }

    @Test
    public void testGetReceiptCallsBunPriceOnce() {
        when(bun.getName()).thenReturn("test bun");
        when(bun.getPrice()).thenReturn(10.0f);

        Burger burger = new Burger();
        burger.setBuns(bun);

        burger.getReceipt();

        verify(bun).getPrice();
    }
}