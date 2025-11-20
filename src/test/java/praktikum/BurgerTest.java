package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient fillingIngredientMock;

    @Mock
    private Ingredient sauceIngredientMock;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsSavesBunInField() {
        burger.setBuns(bunMock);
        assertSame(bunMock, burger.bun);
    }

    @Test
    public void addIngredientIncreasesIngredientsSizeByOne() {
        burger.setBuns(bunMock);
        burger.addIngredient(fillingIngredientMock);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientDecreasesIngredientsSizeByOne() {
        burger.setBuns(bunMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(sauceIngredientMock);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void moveIngredientChangesIngredientOrder() {
        burger.setBuns(bunMock);

        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);

        burger.addIngredient(first);
        burger.addIngredient(second);

        burger.moveIngredient(0, 1);

        assertSame(first, burger.ingredients.get(1));
    }

    @Test
    public void getPriceReturnsSumOfBunAndIngredients() {
        when(bunMock.getPrice()).thenReturn(100.0f);
        when(fillingIngredientMock.getPrice()).thenReturn(20.0f);
        when(sauceIngredientMock.getPrice()).thenReturn(30.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(sauceIngredientMock);

        float expectedPrice = 2 * 100.0f + 20.0f + 30.0f;

        assertEquals(expectedPrice, burger.getPrice(), 0.0001f);
    }

    @Test
    public void getReceiptReturnsFormattedReceiptWithBunIngredientsAndPrice() {
        when(bunMock.getName()).thenReturn("test bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(fillingIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(fillingIngredientMock.getName()).thenReturn("ketchup");
        when(fillingIngredientMock.getPrice()).thenReturn(10.0f);

        when(sauceIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(sauceIngredientMock.getName()).thenReturn("cutlet");
        when(sauceIngredientMock.getPrice()).thenReturn(200.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(fillingIngredientMock);
        burger.addIngredient(sauceIngredientMock);

        float expectedPrice = 2 * 100.0f + 10.0f + 200.0f;

        String expectedReceipt =
                String.format("(==== %s ====)%n", "test bun") +
                        String.format("= %s %s =%n", "sauce", "ketchup") +
                        String.format("= %s %s =%n", "filling", "cutlet") +
                        String.format("(==== %s ====)%n", "test bun") +
                        String.format("%nPrice: %f%n", expectedPrice);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }
}