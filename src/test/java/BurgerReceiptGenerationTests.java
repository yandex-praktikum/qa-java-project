import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class BurgerReceiptGenerationTests {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient fillingIngredientMock;

    @Before

    public void setUp() {
        burger = new Burger();

        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(fillingIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredientMock.getName()).thenReturn("sausage");
        when(fillingIngredientMock.getPrice()).thenReturn(300.0f);
    }

    @Test
    public void shouldGenerationCorrectReceipt() {
        burger.setBuns(bunMock);
        burger.addIngredient(fillingIngredientMock);

        String actualReceipt = burger.getReceipt();

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                "black bun",
                IngredientType.FILLING.toString().toLowerCase(),
                "sausage",
                "black bun",
                500.0f
        );

        assertEquals(expectedReceipt, actualReceipt);

    }


}
