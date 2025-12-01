package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BurgerPriceTest extends BaseTest {

    @Before
    public void setUp() {
        super.setUp();
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockSauce.getPrice()).thenReturn(50f);
        when(mockFilling.getPrice()).thenReturn(75f);
    }

    // Цена бургера с булочкой и двумя ингредиентами
    @Test
    public void testGetPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        assertEquals(325f, burger.getPrice(), 0.01f);
    }

    // Цена только с булочкой, без ингредиентов
    @Test
    public void testGetPriceWithNoIngredients() {
        burger.setBuns(mockBun);
        assertEquals(200f, burger.getPrice(), 0.01f);
    }
}
