package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

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
        burger.addIngredient(ingredientMock1);
        assertEquals(ingredientMock1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveByIndex() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void moveIngredientShouldChangePosition() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldPlaceElementAtNewIndex() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceShouldReturnSumOfBunsAndIngredients() {
        when(bunMock.getPrice()).thenReturn(100f);
        when(ingredientMock1.getPrice()).thenReturn(50f);
        when(ingredientMock2.getPrice()).thenReturn(30f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expectedPrice = 100f * 2 + 50f + 30f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void getReceiptShouldReturnFormattedString() {
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100f);
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn("hot sauce");
        when(ingredientMock1.getPrice()).thenReturn(50f);
        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getName()).thenReturn("cutlet");
        when(ingredientMock2.getPrice()).thenReturn(30f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String expectedReceipt = "(==== black bun ====)\n" +
                "= sauce hot sauce =\n" +
                "= filling cutlet =\n" +
                "(==== black bun ====)\n" +
                "\n" +
                "Price: 280,000000\n";

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}