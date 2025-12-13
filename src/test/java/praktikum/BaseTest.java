package praktikum;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class BaseTest {
    @Mock
    protected Bun mockBun;
    @Mock
    protected Ingredient mockIngredient;
    @Mock
    protected Ingredient mockSauce;
    @Mock
    protected Ingredient mockFilling;

    protected Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

            when(mockBun.getName()).thenReturn("Test Bun");
            when(mockBun.getPrice()).thenReturn(100f);
            when(mockIngredient.getName()).thenReturn("Test Ingredient");
            when(mockIngredient.getPrice()).thenReturn(50f);
            when(mockSauce.getName()).thenReturn("Test Sauce");
            when(mockSauce.getPrice()).thenReturn(50f);
            when(mockFilling.getName()).thenReturn("Test Filling");
            when(mockFilling.getPrice()).thenReturn(75f);

    }
}
