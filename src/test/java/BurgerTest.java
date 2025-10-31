import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;
    private Bun bunMock;
    private Ingredient sauceIngredientMock;
    private Ingredient fillingIngredientMock;

    // Константы для замены magic numbers
    private static final float BUN_PRICE = 100.0f;
    private static final float SAUCE_PRICE = 50.0f;
    private static final float FILLING_PRICE = 70.0f;
    private static final float CHEESE_PRICE = 90.0f;
    private static final float FLOAT_DELTA = 0.0001f;
    private static final int FIRST_POSITION = 0;
    private static final int SECOND_POSITION = 1;
    private static final int INGREDIENTS_COUNT_AFTER_REMOVAL = 2;
    private static final int EXPECTED_INGREDIENTS_COUNT = 2;
    private static final int BUN_MULTIPLIER = 2;
    private static final int INVALID_INDEX_FOR_EMPTY_LIST = 0;

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(BUN_PRICE);

        sauceIngredientMock = mock(Ingredient.class);
        when(sauceIngredientMock.getName()).thenReturn("Hot Sauce");
        when(sauceIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredientMock.getPrice()).thenReturn(SAUCE_PRICE);

        fillingIngredientMock = mock(Ingredient.class);
        when(fillingIngredientMock.getName()).thenReturn("Sausage");
        when(fillingIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredientMock.getPrice()).thenReturn(FILLING_PRICE);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(sauceIngredientMock);
        assertTrue(burger.ingredients.contains(sauceIngredientMock));
    }

    @Test
    public void testRemoveIngredientRemovesCorrectIngredient() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);

        burger.removeIngredient(FIRST_POSITION);

        assertFalse(burger.ingredients.contains(sauceIngredientMock));
    }

    @Test
    public void testRemoveIngredientKeepsOtherIngredients() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);

        burger.removeIngredient(FIRST_POSITION);

        assertTrue(burger.ingredients.contains(fillingIngredientMock));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWhenIndexOutOfBounds() {
        burger.removeIngredient(INVALID_INDEX_FOR_EMPTY_LIST);
    }

    @Test
    public void testMoveIngredientMovesFirstToSecondPosition() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);

        burger.moveIngredient(FIRST_POSITION, SECOND_POSITION);

        assertEquals(fillingIngredientMock, burger.ingredients.get(FIRST_POSITION));
    }

    @Test
    public void testMoveIngredientMovesSecondToFirstPosition() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);

        burger.moveIngredient(FIRST_POSITION, SECOND_POSITION);

        assertEquals(sauceIngredientMock, burger.ingredients.get(SECOND_POSITION));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientWhenIndexOutOfBounds() {
        burger.moveIngredient(FIRST_POSITION, SECOND_POSITION);
    }

    @Test(expected = NullPointerException.class)
    public void testGetPriceWhenBunNotSet() {
        burger.getPrice();
    }

    @Test(expected = NullPointerException.class)
    public void testGetReceiptWhenBunNotSet() {
        burger.getReceipt();
    }

    @Test
    public void testGetPriceWithOnlyBun() {
        burger.setBuns(bunMock);

        float expectedPrice = BUN_PRICE * BUN_MULTIPLIER;
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, FLOAT_DELTA);
    }

    @Test
    public void testGetReceiptWithOnlyBun() {
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                bunMock.getName(),
                bunMock.getName(),
                burger.getPrice()
        );

        assertEquals(expectedReceipt, receipt);
    }

    @Test
    public void testGetReceiptWithMultipleIngredients() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(sauceIngredientMock);

        String receipt = burger.getReceipt();

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                bunMock.getName(),
                sauceIngredientMock.getType().toString().toLowerCase(),
                sauceIngredientMock.getName(),
                fillingIngredientMock.getType().toString().toLowerCase(),
                fillingIngredientMock.getName(),
                sauceIngredientMock.getType().toString().toLowerCase(),
                sauceIngredientMock.getName(),
                bunMock.getName(),
                burger.getPrice()
        );

        assertEquals(expectedReceipt, receipt);
    }

    @Test
    public void testMoveIngredientToSamePositionKeepsFirstIngredient() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);

        Ingredient firstBeforeMove = burger.ingredients.get(FIRST_POSITION);

        burger.moveIngredient(FIRST_POSITION, FIRST_POSITION);

        assertEquals(firstBeforeMove, burger.ingredients.get(FIRST_POSITION));
    }

    @Test
    public void testMoveIngredientToSamePositionKeepsSecondIngredient() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);

        Ingredient secondBeforeMove = burger.ingredients.get(SECOND_POSITION);

        burger.moveIngredient(FIRST_POSITION, FIRST_POSITION);

        assertEquals(secondBeforeMove, burger.ingredients.get(SECOND_POSITION));
    }

    @Test
    public void testRemoveIngredientFromMiddleReducesSize() {
        Ingredient cheeseIngredientMock = mock(Ingredient.class);
        when(cheeseIngredientMock.getName()).thenReturn("Cheese");
        when(cheeseIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(cheeseIngredientMock.getPrice()).thenReturn(CHEESE_PRICE);

        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(cheeseIngredientMock);

        burger.removeIngredient(SECOND_POSITION);

        assertEquals(INGREDIENTS_COUNT_AFTER_REMOVAL, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientFromMiddleKeepsFirstIngredient() {
        Ingredient cheeseIngredientMock = mock(Ingredient.class);
        when(cheeseIngredientMock.getName()).thenReturn("Cheese");
        when(cheeseIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(cheeseIngredientMock.getPrice()).thenReturn(CHEESE_PRICE);

        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(cheeseIngredientMock);

        burger.removeIngredient(SECOND_POSITION);

        assertEquals(sauceIngredientMock, burger.ingredients.get(FIRST_POSITION));
    }

    @Test
    public void testRemoveIngredientFromMiddleKeepsLastIngredient() {
        Ingredient cheeseIngredientMock = mock(Ingredient.class);
        when(cheeseIngredientMock.getName()).thenReturn("Cheese");
        when(cheeseIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(cheeseIngredientMock.getPrice()).thenReturn(CHEESE_PRICE);

        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(cheeseIngredientMock);

        burger.removeIngredient(SECOND_POSITION);

        assertEquals(cheeseIngredientMock, burger.ingredients.get(SECOND_POSITION));
    }

    @Test
    public void testAddMultipleIngredientsIncreasesSize() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);

        assertEquals(EXPECTED_INGREDIENTS_COUNT, burger.ingredients.size());
    }

    @Test
    public void testAddMultipleIngredientsFirstIngredientCorrect() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);

        assertEquals(sauceIngredientMock, burger.ingredients.get(FIRST_POSITION));
    }

    @Test
    public void testAddMultipleIngredientsSecondIngredientCorrect() {
        burger.addIngredient(sauceIngredientMock);
        burger.addIngredient(fillingIngredientMock);

        assertEquals(fillingIngredientMock, burger.ingredients.get(SECOND_POSITION));
    }

    @Test
    public void testBurgerInitialStateIngredientsNotNull() {
        assertNotNull(burger.ingredients);
    }

    @Test
    public void testBurgerInitialStateIngredientsEmpty() {
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testBurgerInitialStateBunIsNull() {
        assertNull(burger.bun);
    }
}