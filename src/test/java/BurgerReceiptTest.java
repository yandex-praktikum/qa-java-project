import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.*;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BurgerReceiptTest {

    private Burger burger;

    @Mock
    private Ingredient ingredient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        burger = new Burger();
        burger.setBuns(new Bun("white bun", 100));

        when(ingredient.getPrice()).thenReturn(50f);
        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(ingredient);
    }

    @Test
    public void getReceiptTest() {
        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("white bun"));
        assertTrue(receipt.contains("filling cutlet"));
        assertTrue(receipt.contains("Price:"));
        assertTrue(receipt.contains("250"));
    }
}
