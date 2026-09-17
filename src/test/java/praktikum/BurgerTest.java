package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private static final float BUN_PRICE = 100f;
    private static final float INGREDIENT_PRICE = 50f;
    private static final float DELTA = 0.001f;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int SIZE_AFTER_ONE_ADD = 1;
    private static final int SIZE_AFTER_REMOVE = 0;

    private static final String BUN_NAME = "black bun";
    private static final String CUTLET_NAME = "cutlet";
    private static final String SAUCE_NAME = "hot sauce";
    private static final String PRICE_LABEL = "Price:";
    private static final String SAUCE_TYPE_LOWER = "sauce";

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBunCorrectly() {
        when(mockBun.getName()).thenReturn(BUN_NAME);
        burger.setBuns(mockBun);
        assertEquals(BUN_NAME, burger.bun.getName());
    }

    @Test
    public void addIngredientShouldIncreaseListSize() {
        burger.addIngredient(mockIngredient);
        assertEquals(SIZE_AFTER_ONE_ADD, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddCorrectIngredient() {
        when(mockIngredient.getName()).thenReturn(CUTLET_NAME);
        burger.addIngredient(mockIngredient);
        assertEquals(CUTLET_NAME, burger.ingredients.get(FIRST_INDEX).getName());
    }

    @Test
    public void removeIngredientShouldDecreaseListSize() {
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(FIRST_INDEX);
        assertEquals(SIZE_AFTER_REMOVE, burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithInvalidIndexShouldThrowException() {
        burger.removeIngredient(FIRST_INDEX);
    }

    @Test
    public void moveIngredientShouldMoveFirstToSecondPosition() {
        Ingredient cutlet = new Ingredient(IngredientType.FILLING, CUTLET_NAME, INGREDIENT_PRICE);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, SAUCE_NAME, INGREDIENT_PRICE);
        burger.addIngredient(cutlet);
        burger.addIngredient(sauce);
        burger.moveIngredient(FIRST_INDEX, SECOND_INDEX);
        assertEquals(sauce, burger.ingredients.get(FIRST_INDEX));
    }

    @Test
    public void moveIngredientShouldMoveIngredientFromSecondToFirstPosition() {
        Ingredient cutlet = new Ingredient(IngredientType.FILLING, CUTLET_NAME, INGREDIENT_PRICE);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, SAUCE_NAME, INGREDIENT_PRICE);
        burger.addIngredient(cutlet);
        burger.addIngredient(sauce);
        burger.moveIngredient(SECOND_INDEX, FIRST_INDEX);
        assertEquals(sauce, burger.ingredients.get(FIRST_INDEX));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithInvalidIndexShouldThrowException() {
        burger.moveIngredient(FIRST_INDEX, SECOND_INDEX);
    }

    @Test
    public void getPriceShouldReturnCorrectPriceWithBunAndIngredients() {
        when(mockBun.getPrice()).thenReturn(BUN_PRICE);
        when(mockIngredient.getPrice()).thenReturn(INGREDIENT_PRICE);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        float expectedPrice = BUN_PRICE * 2 + INGREDIENT_PRICE;
        assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getPriceShouldReturnOnlyBunPriceWhenNoIngredients() {
        when(mockBun.getPrice()).thenReturn(BUN_PRICE);
        burger.setBuns(mockBun);
        float expectedPrice = BUN_PRICE * 2;
        assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getReceiptShouldContainBunName() {
        when(mockBun.getName()).thenReturn(BUN_NAME);
        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(BUN_NAME));
    }

    @Test
    public void getReceiptShouldContainIngredientName() {
        when(mockBun.getName()).thenReturn(BUN_NAME);
        when(mockIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient.getName()).thenReturn(CUTLET_NAME);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(CUTLET_NAME));
    }

    @Test
    public void getReceiptShouldContainIngredientTypeLowerCase() {
        when(mockBun.getName()).thenReturn(BUN_NAME);
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getName()).thenReturn(SAUCE_NAME);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(SAUCE_TYPE_LOWER));
    }

    @Test
    public void getReceiptShouldContainPrice() {
        when(mockBun.getName()).thenReturn(BUN_NAME);
        when(mockBun.getPrice()).thenReturn(BUN_PRICE);
        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains(PRICE_LABEL));
    }
}