import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTestParameterized {

    private Burger burger;
    private final Bun bunMock;
    private final Ingredient firstIngredientMock;
    private final Ingredient secondIngredientMock;

    // Константы для замены magic numbers
    private static final float BUN_PRICE_PARAM = 100.0f;
    private static final float FIRST_INGREDIENT_PRICE = 50.0f;
    private static final float SECOND_INGREDIENT_PRICE = 70.0f;
    private static final float FLOAT_DELTA = 0.0001f;
    private static final int BUN_MULTIPLIER = 2;

    public BurgerTestParameterized(Bun bunMock, Ingredient firstIngredientMock, Ingredient secondIngredientMock) {
        this.bunMock = bunMock;
        this.firstIngredientMock = firstIngredientMock;
        this.secondIngredientMock = secondIngredientMock;
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Bun bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_PARAM);

        Ingredient firstIngredientMock = mock(Ingredient.class);
        when(firstIngredientMock.getName()).thenReturn("Hot Sauce");
        when(firstIngredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(firstIngredientMock.getPrice()).thenReturn(FIRST_INGREDIENT_PRICE);

        Ingredient secondIngredientMock = mock(Ingredient.class);
        when(secondIngredientMock.getName()).thenReturn("Sausage");
        when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(secondIngredientMock.getPrice()).thenReturn(SECOND_INGREDIENT_PRICE);

        return Arrays.asList(new Object[][]{
                {bunMock, firstIngredientMock, secondIngredientMock}
        });
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        float expectedPrice = BUN_PRICE_PARAM * BUN_MULTIPLIER + FIRST_INGREDIENT_PRICE + SECOND_INGREDIENT_PRICE;
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, FLOAT_DELTA);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bunMock);
        burger.addIngredient(firstIngredientMock);
        burger.addIngredient(secondIngredientMock);

        String receipt = burger.getReceipt();

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                bunMock.getName(),
                firstIngredientMock.getType().toString().toLowerCase(),
                firstIngredientMock.getName(),
                secondIngredientMock.getType().toString().toLowerCase(),
                secondIngredientMock.getName(),
                bunMock.getName(),
                burger.getPrice()
        );

        assertEquals(expectedReceipt, receipt);
    }
}