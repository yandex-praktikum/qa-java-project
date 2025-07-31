



import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    private float bunPrice;
    private float ingredientPrice;
    private float expectedPrice;
    private String bunName;
    private String ingredientName;
    private IngredientType ingredientType;
    private String expectedReceipt;

    public BurgerTest(float bunPrice, float ingredientPrice, float expectedPrice,
                      String bunName, String ingredientName, IngredientType ingredientType, String expectedReceipt) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;
        this.bunName = bunName;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.expectedReceipt = expectedReceipt;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockIngredient.getPrice()).thenReturn(ingredientPrice);
        when(mockBun.getName()).thenReturn(bunName);
        when(mockIngredient.getName()).thenReturn(ingredientName);
        when(mockIngredient.getType()).thenReturn(ingredientType);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 2.0f, 3.0f, 7.0f, "Brioche", "Cheese", IngredientType.FILLING,
                        "(==== Brioche ====)" + System.lineSeparator() +
                                "= filling Cheese =" + System.lineSeparator() +
                                "(==== Brioche ====)" + System.lineSeparator() + System.lineSeparator() +
                                "Price: 7,000000" + System.lineSeparator() },
                { 1.5f, 2.5f, 5.5f, "Wheat", "Lettuce", IngredientType.FILLING,
                        "(==== Wheat ====)" + System.lineSeparator() +
                                "= filling Lettuce =" + System.lineSeparator() +
                                "(==== Wheat ====)" + System.lineSeparator() + System.lineSeparator() +
                                "Price: 5,500000" + System.lineSeparator() },
                { 3.0f, 1.0f, 7.0f, "Sesame", "Bacon", IngredientType.FILLING,
                        "(==== Sesame ====)" + System.lineSeparator() +
                                "= filling Bacon =" + System.lineSeparator() +
                                "(==== Sesame ====)" + System.lineSeparator() + System.lineSeparator() +
                                "Price: 7,000000" + System.lineSeparator() }
        });
    }

    @Test
    public void testGetPrice() {
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void testGetReceipt() {
        assertEquals(expectedReceipt, burger.getReceipt());
    }

    @Test
    public void testAddIngredient() {
        Ingredient newIngredient = mock(Ingredient.class);
        burger.addIngredient(newIngredient);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        Ingredient anotherIngredient = mock(Ingredient.class);
        burger.addIngredient(anotherIngredient);

        burger.moveIngredient(1, 0);
        assertEquals(anotherIngredient, burger.ingredients.get(0));
        assertEquals(mockIngredient, burger.ingredients.get(1));
    }
}
