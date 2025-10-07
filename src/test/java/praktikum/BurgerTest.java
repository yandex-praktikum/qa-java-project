package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        burger.setBuns(bun);
    }

    @Test
    public void setsBun() {
        Mockito.when(bun.getName()).thenReturn("тестовая булка");
        assertTrue(burger.getReceipt().contains("тестовая булка"));
    }

    @Test
    public void addsIngredient() {
        burger.addIngredient(ingredient1);

        Mockito.when(bun.getName()).thenReturn("тестовая булка");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient1.getName()).thenReturn("тестовый ингредиент");
        assertTrue(burger.getReceipt().contains("= filling тестовый ингредиент ="));
    }

    @Test
    public void removesIngredient() {
        Mockito.when(ingredient1.getName()).thenReturn("удаляемый ингредиент");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(bun.getName()).thenReturn("тестовая булка");

        burger.addIngredient(ingredient1);
        assertTrue(burger.getReceipt().contains("удаляемый ингредиент"));

        burger.removeIngredient(0);
        assertFalse(burger.getReceipt().contains("удаляемый ингредиент"));
    }

    @Test
    public void movesIngredient() {
        Mockito.when(bun.getName()).thenReturn("тестовая булка");
        Mockito.when(ingredient1.getName()).thenReturn("ингредиент A");
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient2.getName()).thenReturn("ингредиент B");
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        String receipt = burger.getReceipt();

        assertTrue(receipt.indexOf("ингредиент B") < receipt.indexOf("ингредиент A"));
    }

    @Test
    public void calculatesPrice() {
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient1.getPrice()).thenReturn(50f);
        Mockito.when(ingredient2.getPrice()).thenReturn(200f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float actualPrice = burger.getPrice();

        assertEquals(450f, actualPrice, 0.0f);
    }

    @Test
    public void generatesFormattedReceipt() {
        Mockito.when(bun.getName()).thenReturn("тестовая булка");
        Mockito.when(bun.getPrice()).thenReturn(125.5f);

        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn("тестовый соус");
        Mockito.when(ingredient1.getPrice()).thenReturn(15.0f);

        burger.addIngredient(ingredient1);

        float expectedPrice = 125.5f * 2 + 15.0f;

        String expectedReceipt = String.format(
                "(==== тестовая булка ====)%n" +
                        "= sauce тестовый соус =%n" +
                        "(==== тестовая булка ====)%n" +
                        "%nPrice: %f%n", expectedPrice
        );

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }
}
