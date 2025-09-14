package praktikum;

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
import static praktikum.TestConstants.*;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {
    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expectedPrice;
    
    private Burger burger;
    
    @Mock
    private Bun bunMock;
    
    @Mock
    private Ingredient ingredientMock;

    public BurgerParameterizedTest(float bunPrice, float[] ingredientPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Булка: {0} руб, Ингредиенты: {1}, Ожидаемая цена: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {BUN_PRICE_100, new float[]{}, EXPECTED_PRICE_200},
                {BUN_PRICE_50, new float[]{INGREDIENT_PRICE_25}, EXPECTED_PRICE_125},
                {BUN_PRICE_75, new float[]{INGREDIENT_PRICE_30, INGREDIENT_PRICE_20}, EXPECTED_PRICE_200},
                {BUN_PRICE_100, new float[]{INGREDIENT_PRICE_50, INGREDIENT_PRICE_30, INGREDIENT_PRICE_20}, EXPECTED_PRICE_300},
                {BUN_PRICE_ZERO, new float[]{INGREDIENT_PRICE_ZERO}, EXPECTED_PRICE_ZERO},
                {BUN_PRICE_100, new float[]{INGREDIENT_PRICE_1, INGREDIENT_PRICE_1}, EXPECTED_PRICE_202},
                {150.5f, new float[]{INGREDIENT_PRICE_25_5, INGREDIENT_PRICE_30, INGREDIENT_PRICE_44}, EXPECTED_PRICE_400_5},
                {BUN_PRICE_200, new float[]{BUN_PRICE_100}, EXPECTED_PRICE_500},
                {BUN_PRICE_33_33, new float[]{INGREDIENT_PRICE_11_11, INGREDIENT_PRICE_22_22, INGREDIENT_PRICE_33_33}, EXPECTED_PRICE_133_32}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void testGetPriceWithDifferentIngredients() {
        bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bunMock);
        
        for (float ingredientPrice : ingredientPrices) {
            Ingredient ingredient = mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(ingredientPrice);
            burger.addIngredient(ingredient);
        }
        
        assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }
}