package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private final Burger burger = new Burger();
    @Mock
    private Bun bunMock;
    @Mock
    private Ingredient ingredientMock1;
    @Mock
    private Ingredient ingredientMock2;

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientMock1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        burger.moveIngredient(1, 0);

        assertEquals(ingredientMock2, burger.ingredients.get(0));
        assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceWithIngredients() {
        Mockito.when(bunMock.getPrice()).thenReturn(100f);
        Mockito.when(ingredientMock1.getPrice()).thenReturn(50f);
        Mockito.when(ingredientMock2.getPrice()).thenReturn(25f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expectedPrice = 100f * 2 + 50f + 25f;

        assertEquals(expectedPrice, burger.getPrice(), 0.0001f);
    }

    @Test
    public void testGetReceipt() {
        Mockito.when(bunMock.getName()).thenReturn("black bun");
        Mockito.when(bunMock.getPrice()).thenReturn(100f);

        Mockito.when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientMock1.getName()).thenReturn("hot sauce");
        Mockito.when(ingredientMock1.getPrice()).thenReturn(100f);

        Mockito.when(ingredientMock2.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientMock2.getName()).thenReturn("cutlet");
        Mockito.when(ingredientMock2.getPrice()).thenReturn(200f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String expectedReceipt = String.format(
                "(==== black bun ====)%n" +
                        "= sauce hot sauce =%n" +
                        "= filling cutlet =%n" +
                        "(==== black bun ====)%n" +
                        "%n" +
                        "Price: 500.000000%n"
        );

        String actualReceipt = burger.getReceipt();

        assertEquals("Чек не совпадает с ожидаемым", expectedReceipt, actualReceipt);
    }

}