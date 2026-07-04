import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.junit.Before;
import java.util.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import praktikum.*;

@RunWith(Parameterized.class)
public class BurgerPriceParametrizedTest {

    private final float expectedPrice;
    private final List<Float> ingredientsPrices;

    public BurgerPriceParametrizedTest(float expectedPrice, List<Float> ingredientsPrices) {
        this.expectedPrice = expectedPrice;
        this.ingredientsPrices = ingredientsPrices;
    }

    private Burger burger;

    @Before
    public void setUp() {
        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(20.0f);
        when(bunMock.getName()).thenReturn("testBun");

        burger = new Burger();
        burger.setBuns(bunMock);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {40.0f, Collections.emptyList()},
                {50.0f, Arrays.asList(10.0f)},
                {55.0f, Arrays.asList(15.0f)},
                {65.0f, Arrays.asList(10.0f + 15.0f)}
        });
    }

    @Test
    public void priceTest() {
        List<Ingredient> ingredientsToAdd = new ArrayList<>();
        for (float price : ingredientsPrices){
            Ingredient ingredientMock = mock(Ingredient.class);
            when(ingredientMock.getPrice()).thenReturn(price);
            ingredientsToAdd.add(ingredientMock);
        }
        ingredientsToAdd.forEach(burger::addIngredient);

        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.01f);
    }
}
