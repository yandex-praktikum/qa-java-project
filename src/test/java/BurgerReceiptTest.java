package praktikum;
import org.junit.runner.RunWith;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {
    private Burger burger;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;
    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;
    private final String answerReceipt;

    public BurgerReceiptTest(String bunName, float bunPrice, IngredientType ingredientType, String ingredientName, float ingredientPrice, String expectedReceipt) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
        this.answerReceipt = expectedReceipt;}

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"red bun", 300.0f, IngredientType.FILLING, "cutlet", 100.0f,
                        "(==== red bun ====)\n= filling cutlet =\n(==== red bun ====)\n\nPrice: 700,000000\n"},
                {"white bun", 200.0f, IngredientType.SAUCE, "sour cream", 200.0f,
                        "(==== white bun ====)\n= sauce sour cream =\n(==== white bun ====)\n\nPrice: 600,000000\n"},
                {"black bun", 100.0f, IngredientType.FILLING, "sausage", 300.0f,
                        "(==== black bun ====)\n= filling sausage =\n(==== black bun ====)\n\nPrice: 500,000000\n"}
        });}

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();}

    @Test
    public void getReceiptReturnsRightAnswer() {
        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getType()).thenReturn(ingredientType);
        Mockito.when(ingredient.getName()).thenReturn(ingredientName);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        assertEquals(answerReceipt, burger.getReceipt());
    }
}