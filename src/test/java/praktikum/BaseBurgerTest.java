package praktikum;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

/**
 * Base test class with common mocks.
 * Contains setup for mock objects for buns and ingredients.
 */
public class BaseBurgerTest {

    protected Burger burger;

    @Mock
    protected Bun blackBun;

    @Mock
    protected Bun whiteBun;

    @Mock
    protected Ingredient hotSauce;

    @Mock
    protected Ingredient cutlet;

    @Mock
    protected Ingredient dinosaur;

    @Mock
    protected Ingredient sausage;

    @Mock
    protected Ingredient sourCream;

    @Before
    public void setUpMocks() {
        MockitoAnnotations.openMocks(this);

        // Setup bun mocks
        when(blackBun.getName()).thenReturn("black bun");
        when(blackBun.getPrice()).thenReturn(100.0f);

        when(whiteBun.getName()).thenReturn("white bun");
        when(whiteBun.getPrice()).thenReturn(200.0f);

        // Setup sauce mocks
        when(hotSauce.getName()).thenReturn("hot sauce");
        when(hotSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(hotSauce.getPrice()).thenReturn(100.0f);

        when(sourCream.getName()).thenReturn("sour cream");
        when(sourCream.getType()).thenReturn(IngredientType.SAUCE);
        when(sourCream.getPrice()).thenReturn(200.0f);

        // Setup filling mocks
        when(cutlet.getName()).thenReturn("cutlet");
        when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        when(cutlet.getPrice()).thenReturn(100.0f);

        when(dinosaur.getName()).thenReturn("dinosaur");
        when(dinosaur.getType()).thenReturn(IngredientType.FILLING);
        when(dinosaur.getPrice()).thenReturn(200.0f);

        when(sausage.getName()).thenReturn("sausage");
        when(sausage.getType()).thenReturn(IngredientType.FILLING);
        when(sausage.getPrice()).thenReturn(300.0f);

        burger = new Burger();
    }

    /**
     * Helper method to extract price from receipt string.
     * Handles both comma and dot decimal separators.
     */
    protected float extractPriceFromReceipt(String receipt) {
        String[] lines = receipt.split("\\r?\\n");
        for (String line : lines) {
            if (line.startsWith("Price: ")) {
                String priceStr = line.substring("Price: ".length());
                // Replace comma with dot for parsing
                priceStr = priceStr.replace(',', '.');
                return Float.parseFloat(priceStr);
            }
        }
        throw new IllegalArgumentException("Price not found in receipt");
    }
}