package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;

    public BurgerTest(IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}, {1}, {2}")
    public static Object[][] getData() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 30f},
                {IngredientType.FILLING, "cutlet", 50f}
        };
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsCorrectly() {
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientIncreaseIngredientsSize() {

        burger.addIngredient(firstIngredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientSaveCorrectIngredient() {

        burger.addIngredient(firstIngredient);

        assertEquals(firstIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientFromList() {

        burger.addIngredient(firstIngredient);

        burger.removeIngredient(0);

        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientMoveSecondIngredientToFirstPosition() {

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientMoveFirstIngredientToSecondPosition() {

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceReturnCorrectPrice() {

        when(bun.getPrice()).thenReturn(100f);

        when(firstIngredient.getPrice()).thenReturn(ingredientPrice);

        when(secondIngredient.getPrice()).thenReturn(25f);

        burger.setBuns(bun);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float actualPrice = burger.getPrice();

        assertEquals(225f + ingredientPrice, actualPrice, 0.001f);
    }

    @Test
    public void getReceiptReturnCorrectReceipt() {

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(firstIngredient.getType()).thenReturn(ingredientType);
        when(firstIngredient.getName()).thenReturn(ingredientName);
        when(firstIngredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);

        String expected = String.format(
                "(==== black bun ====)%n" +
                        "= %s %s =%n" +
                        "(==== black bun ====)%n" +
                        "%nPrice: %f%n",
                ingredientType.toString().toLowerCase(),
                ingredientName,
                200f + ingredientPrice
        );

        assertEquals(expected, burger.getReceipt());
    }
}
