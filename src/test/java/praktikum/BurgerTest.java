package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock1;

    @Mock
    private Ingredient ingredientMock2;

    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;

    public BurgerTest(String bunName, float bunPrice, IngredientType ingredientType,
                      String ingredientName, float ingredientPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"black bun", 100.0f, IngredientType.SAUCE, "hot sauce", 50.0f},
                {"white bun", 200.0f, IngredientType.FILLING, "cutlet", 150.0f},
                {"red bun", 300.0f, IngredientType.SAUCE, "sour cream", 75.0f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(bunPrice);

        when(ingredientMock1.getType()).thenReturn(ingredientType);
        when(ingredientMock1.getName()).thenReturn(ingredientName);
        when(ingredientMock1.getPrice()).thenReturn(ingredientPrice);

        when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock2.getName()).thenReturn("cheese");
        when(ingredientMock2.getPrice()).thenReturn(80.0f);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertSame(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientMock1);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredientMock1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.moveIngredient(0, 1);

        assertEquals(2, burger.ingredients.size());
        assertSame(ingredientMock2, burger.ingredients.get(0));
        assertSame(ingredientMock1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expectedPrice = (bunPrice * 2) + ingredientPrice + 80.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetPriceWithoutBunThrowsNPE() {
        burger.getPrice();
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains(bunName));
        assertTrue(receipt.contains(ingredientName));
        float expectedPrice = (bunPrice * 2) + ingredientPrice;
        assertTrue(receipt.contains(String.format("Price: %f", expectedPrice)));
    }
    @Test
    public void testGetReceiptWithoutBunThrowsNPE() {
        burger.getReceipt();
    }

    @Test
    public void testRemoveIngredientWithInvalidIndex() {
        burger.removeIngredient(0);
    }

    @Test
    public void testMoveIngredientWithInvalidIndex() {
        burger.addIngredient(ingredientMock1);
        burger.moveIngredient(5, 0);
    }

    @Test
    public void testMoveIngredientWithInvalidNewIndex() {
        burger.addIngredient(ingredientMock1);
        burger.moveIngredient(0, 5);
    }
}