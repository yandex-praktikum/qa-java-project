import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class BurgerReceiptGenerationTests {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    @Before

    public void setUp() {
        burger = new Burger();

        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock.getName()).thenReturn("sausage");
        when(ingredientMock.getPrice()).thenReturn(300.0f);
    }

    @Test
    public void shouldGenerationCorrectReceipt() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("black bun"));
        assertTrue(receipt.contains("sausage"));

    }


}
