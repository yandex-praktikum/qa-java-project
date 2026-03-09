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

    private final IngredientType type;
    private final String ingredientName;
    private final float ingredientPrice;

    public BurgerTest(IngredientType type, String ingredientName, float ingredientPrice) {
        this.type = type;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters(name = "type={0}, name={1}, price={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.FILLING, "cutlet", 200f},
                {IngredientType.SAUCE, "sour cream", 50f},
                {IngredientType.FILLING, "dinosaur", 300f},
        });
    }

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.setBuns(mockBun);

        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockIngredient.getType()).thenReturn(type);
        when(mockIngredient.getName()).thenReturn(ingredientName);
        when(mockIngredient.getPrice()).thenReturn(ingredientPrice);
    }

    @Test
    public void setBunsSetsCorrectBun() {
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void addIngredientAddsToList() {
        burger.addIngredient(mockIngredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientRemovesFromList() {
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientChangesOrder() {
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.moveIngredient(1, 0);

        assertEquals(second, burger.ingredients.get(0));
        assertEquals(first, burger.ingredients.get(1));
    }

    @Test
    public void getPriceReturnsBunTwicePlusIngredients() {
        burger.addIngredient(mockIngredient);

        float expected = mockBun.getPrice() * 2 + ingredientPrice;
        assertEquals(expected, burger.getPrice(), 0.001f);
    }

    @Test
    public void getPriceWithNoIngredientReturnsBunPriceOnly() {
        float expected = mockBun.getPrice() * 2;
        assertEquals(expected, burger.getPrice(), 0.001f);
    }

    @Test
    public void getReceiptContainsBunName() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("black bun"));
    }

    @Test
    public void getReceiptContainsIngredient() {
        burger.addIngredient(mockIngredient);
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains(ingredientName));
        assertTrue(receipt.contains(type.toString().toLowerCase()));
    }

    @Test
    public void getReceiptContainsPrice() {
        burger.addIngredient(mockIngredient);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price:"));
    }
}