package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {


    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expectedTotalPrice;

    
    public BurgerPriceParameterizedTest(float bunPrice, float[] ingredientPrices, float expectedTotalPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    
    @Parameterized.Parameters(name = "Цена бургера (булка: {0}, ингредиенты: {1}) = {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                
                {10.0f, new float[]{}, 20.0f},
                {100.0f, new float[]{50.0f}, 250.0f},
                {125.5f, new float[]{200.0f, 25.5f}, 476.5f}
        });
    }

    @Test
    public void getPriceShouldCalculateCorrectly() {
        Burger burger = new Burger();

        
        Bun mockBun = Mockito.mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(mockBun);

        
        for (float ingredientPrice : ingredientPrices) {
            Ingredient mockIngredient = Mockito.mock(Ingredient.class);
            when(mockIngredient.getPrice()).thenReturn(ingredientPrice);
            burger.addIngredient(mockIngredient);
        }

        
        assertEquals("Расчет цены неверен", expectedTotalPrice, burger.getPrice(), 0.001f);
    }
}
