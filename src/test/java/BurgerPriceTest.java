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
public class BurgerPriceTest {
    private Burger burger;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;
    private final float bunPrice;
    private final float ingredientPrice;
    private final float answerPrice;

    public BurgerPriceTest(float bunPrice, float ingredientPrice, float answerPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrice = ingredientPrice;
        this.answerPrice = answerPrice;}

    @Parameterized.Parameters(name = "Bun: {0}, Ingredients: {1} => Sum: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 100.0f, 1200.0f, 1400.0f },
                { 300.0f, 0.0f, 600.0f },
                { 0.0f, 100.0f, 100.0f },
                { 0.0f, 0.0f, 0.0f }
        });}

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();}

    @Test
    public void getPriceReturnsCorrectValue() {
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        assertEquals(answerPrice, burger.getPrice(), 0.001f);
    }
}