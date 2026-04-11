package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private static final float BUN_PRICE = 100f;
    private static final float SAUCE_PRICE = 50f;
    private static final float FILLING_PRICE = 30f;

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient sauceMock;

    @Mock
    private Ingredient fillingMock;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void addIngredientShouldAddToList() {
        burger.addIngredient(sauceMock);
        assertEquals(sauceMock, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveByIndex() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void moveIngredientShouldChangePosition() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        burger.moveIngredient(0, 1);
        assertEquals(fillingMock, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldPlaceElementAtNewIndex() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        burger.moveIngredient(0, 1);
        assertEquals(sauceMock, burger.ingredients.get(1));
    }

    @Test
    public void getPriceShouldReturnSumOfBunsAndIngredients() {
        when(bunMock.getPrice()).thenReturn(BUN_PRICE);
        when(sauceMock.getPrice()).thenReturn(SAUCE_PRICE);
        when(fillingMock.getPrice()).thenReturn(FILLING_PRICE);

        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        float expectedPrice = BUN_PRICE * 2 + SAUCE_PRICE + FILLING_PRICE;
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void getReceiptShouldReturnFormattedString() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(BUN_PRICE);
        when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceMock.getName()).thenReturn("hot sauce");
        when(sauceMock.getPrice()).thenReturn(SAUCE_PRICE);
        when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingMock.getName()).thenReturn("cutlet");
        when(fillingMock.getPrice()).thenReturn(FILLING_PRICE);

        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        float totalPrice = BUN_PRICE * 2 + SAUCE_PRICE + FILLING_PRICE;
        String expectedReceipt = "(==== black bun ====)\n" +
                "= sauce hot sauce =\n" +
                "= filling cutlet =\n" +
                "(==== black bun ====)\n" +
                "\n" +
                "Price: " + String.format("%f", totalPrice) + "\n";

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}