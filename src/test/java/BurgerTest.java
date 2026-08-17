import praktikum.Burger;
import praktikum.Bun;
import praktikum.Ingredient;
import praktikum.IngredientType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
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

    private String bunName;
    private float bunPrice;
    private float ingredientPrice1;
    private float ingredientPrice2;
    private IngredientType ingredientType1;
    private IngredientType ingredientType2;
    private String ingredientName1;
    private String ingredientName2;

    public BurgerTest(String bunName, float bunPrice,
                      String ingredientName1, float ingredientPrice1, IngredientType ingredientType1,
                      String ingredientName2, float ingredientPrice2, IngredientType ingredientType2) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientName1 = ingredientName1;
        this.ingredientPrice1 = ingredientPrice1;
        this.ingredientType1 = ingredientType1;
        this.ingredientName2 = ingredientName2;
        this.ingredientPrice2 = ingredientPrice2;
        this.ingredientType2 = ingredientType2;

    }
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"white bun", 100, "cutlet", 150, IngredientType.FILLING,
                        "hot sauce", 50, IngredientType.SAUCE},
                {"black bun", 150, "dinosaur", 200, IngredientType.FILLING,
                        "sour cream", 70, IngredientType.SAUCE},
        });
    }
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(mockBun.getName()).thenReturn(bunName); // При вызове getName вернуть bunName
        when(mockBun.getPrice()).thenReturn(bunPrice); // При вызове getPrice вернуть bunPrice

        when(mockIngredient1.getName()).thenReturn(ingredientName1); // При вызове getName вернуть ingredientName1
        when(mockIngredient1.getPrice()).thenReturn(ingredientPrice1); // При вызове getPrice вернуть ingredientPrice1
        when(mockIngredient1.getType()).thenReturn(ingredientType1); // При вызове getType вернуть ingredientType1

        when(mockIngredient2.getName()).thenReturn(ingredientName2); // При вызове getName вернуть ingredientName2
        when(mockIngredient2.getPrice()).thenReturn(ingredientPrice2); // При вызове getPrice вернуть ingredientPrice2
        when(mockIngredient2.getType()).thenReturn(ingredientType2); // При вызове getType вернуть ingredientType2
    }

    @Test
    public void testSetBunsShouldSetBun() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredientShouldAddCorrectIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientShouldRemoveCorrectIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient2, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithInvalidIndexShouldThrowException() {
        burger.removeIngredient(0);
    }


    @Test
    public void testMoveIngredientShouldChangePositions() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.moveIngredient(0, 1);
        assertEquals(mockIngredient2, burger.ingredients.get(0));
        assertEquals(mockIngredient1, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientWithInvalidIndexShouldThrowException() {
        burger.moveIngredient(0, 1);
    }

    @Test
    public void testGetPriceShouldCalculateCorrectPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = bunPrice * 2 + ingredientPrice1 + ingredientPrice2;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);

        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    public void testGetReceiptShouldReturnCorrectReceipt() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float totalPrice = bunPrice * 2 + ingredientPrice1 + ingredientPrice2;
        String expected = String.format("(==== %s ====)%n", bunName) +
                String.format("= %s %s =%n", ingredientType1.toString().toLowerCase(), ingredientName1) +
                String.format("= %s %s =%n", ingredientType2.toString().toLowerCase(), ingredientName2) +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", totalPrice);

        assertEquals(expected, burger.getReceipt());

        verify(mockBun, times(2)).getName();
        verify(mockIngredient1, times(1)).getName();
        verify(mockIngredient1, times(1)).getType();
        verify(mockIngredient2, times(1)).getName();
        verify(mockIngredient2, times(1)).getType();
    }
}