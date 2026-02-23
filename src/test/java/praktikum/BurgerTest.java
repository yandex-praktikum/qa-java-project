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
    private Ingredient sauceMock;

    @Mock
    private Ingredient fillingMock;

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

    @Parameterized.Parameters(name = "Булка: {0} (цена {1}), ингредиент: {2} {3} (цена {4})")
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

        when(sauceMock.getType()).thenReturn(ingredientType);
        when(sauceMock.getName()).thenReturn(ingredientName);
        when(sauceMock.getPrice()).thenReturn(ingredientPrice);

        when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingMock.getName()).thenReturn("cheese");
        when(fillingMock.getPrice()).thenReturn(80.0f);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(sauceMock);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);
        burger.moveIngredient(0, 1);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

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
        burger.addIngredient(sauceMock);
        String receipt = burger.getReceipt();
        assertNotNull(receipt);
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
        burger.addIngredient(sauceMock);
        burger.moveIngredient(5, 0);
    }

    @Test
    public void testMoveIngredientWithInvalidNewIndex() {
        burger.addIngredient(sauceMock);
        burger.moveIngredient(0, 5);
    }
}